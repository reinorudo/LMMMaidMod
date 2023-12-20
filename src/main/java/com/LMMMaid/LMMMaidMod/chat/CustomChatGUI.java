package com.LMMMaid.LMMMaidMod.chat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.LMMMaid.LMMMaidMod.entity.LMMMaidEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

public class CustomChatGUI extends Screen {
    private TextFieldWidget textField;
    private List<String> maidChatHistory;
    private List<String> playerChatHistory;
    private List<String> combinedChat; // 追加: combined chat history as a member variable
    private int scrollOffset;
    private int visibleLines;

    public CustomChatGUI() {
        super(new StringTextComponent("Custom Chat"));
        this.playerChatHistory = new ArrayList<>();
        this.maidChatHistory = new ArrayList<>();
        this.combinedChat = new ArrayList<>(); // 初期化
        this.scrollOffset = 0;
        this.visibleLines = 1000;//最大表示行数
    }

    @Override
    protected void init() {
        this.textField = new TextFieldWidget(this.font, this.width / 2 - 100, this.height - 30, 200, 20, new StringTextComponent(""));
        this.addButton(new Button(this.width / 2 + 104, this.height - 31, 50, 20, new StringTextComponent("Send"), button -> this.sendMessagePlayer()));
        this.textField.setFocus(true);
        this.textField.setMaxLength(1000); // 文字数制限を設定

        int closeButtonWidth = 20;
        int closeButtonHeight = 20;
        int closeButtonX = this.width - closeButtonWidth - 5; // 右端から5pxの位置
        int closeButtonY = 5; // 上端から5pxの位置

        this.addButton(new Button(closeButtonX, closeButtonY, closeButtonWidth, closeButtonHeight, new StringTextComponent("X"), button -> this.onClose()));

    }

    private void combineChat() {
        combinedChat.clear(); // リストをクリアして新しい履歴で更新
        int maxLength = Math.max(playerChatHistory.size(), maidChatHistory.size());
    
        for (int i = 0; i < maxLength; i++) {
            if (i < maidChatHistory.size()) {
                combinedChat.add("MAID : " + maidChatHistory.get(i));
            }
            if (i < playerChatHistory.size()) {
                combinedChat.add("YOU : " + playerChatHistory.get(i));
            }
        }
    }
    
    
    
    private void sendMessagePlayer() {
        String text = this.textField.getValue();
        if (!text.isEmpty()) {
            this.playerChatHistory.add(0, text);
            this.textField.setValue(""); // テキストを消す
            this.textField.insertText("");
            this.scrollOffset = 0; // 送信後、一番下にスクロールする

            sendMessageMaid(text);
        }
    }

    private void sendMessageMaid(String playerMessage){
        String maidReply;
        if (playerMessage.contains("こんにちは")) {
            maidReply = "ようこそ";
        } else {
            maidReply = "わかりません";
        }
        this.maidChatHistory.add(0, maidReply);
        this.scrollOffset = 0; // 送信後、一番下にスクロールする
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack); // 背景を描画
        this.combineChat(); // 組み合わせたチャット履歴を更新
        int chatY = this.height - 50; // チャットを表示する初期Y座標
        // 表示開始行と終了行を計算
        int startLine = Math.max(0, this.combinedChat.size() - this.visibleLines - this.scrollOffset);
        int endLine = Math.min(this.combinedChat.size() - this.scrollOffset, startLine + this.visibleLines);
    

        for (int i = startLine; i < endLine; i++) {
            String message = combinedChat.get(i);
            int bgColor = message.startsWith("MAID : ") ? 0x80888888 : 0x8000FF00;

            // ITextPropertiesのリストを取得してStringに変換
            List<ITextProperties> lines = this.font.getSplitter().splitLines(message, this.width - 16, Style.EMPTY);
            List<String> stringLines = lines.stream().map(ITextProperties::getString).collect(Collectors.toList());
            Collections.reverse(stringLines); // 逆順にして上から描画

            for (String line : stringLines) {
                int lineLength = this.font.width(line); // テキストの長さを取得
                // ここで背景を描画します。+4 と +2 は余白のための調整値です。
                AbstractGui.fill(matrixStack, 8, chatY - this.font.lineHeight, 8 + lineLength + 4, chatY, bgColor);
                this.font.draw(matrixStack, line, 10, chatY - this.font.lineHeight, 0xFFFFFF); // テキストを描画
                chatY -= this.font.lineHeight; // 次の行のY座標を更新
            }
         chatY -= 4; // メッセージ間の余白
        }

        this.textField.render(matrixStack, mouseX, mouseY, partialTicks); // テキストフィールドを描画
        super.render(matrixStack, mouseX, mouseY, partialTicks); // その他の要素を描画
    }



    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        int scrollAmount = (int) delta; // スクロールの方向と量を示す（通常、1 または -1）
        // スクロールオフセットを更新
        this.scrollOffset = Math.max(0, Math.min(this.scrollOffset - scrollAmount, Math.max(0, this.combinedChat.size() - this.visibleLines)));
        return true;
}
    

    
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            onClose(); // GUI画面を閉じる
            return true;
        }
        return this.textField.keyPressed(keyCode, scanCode, modifiers) || super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char typedChar, int keyCode) {
        this.textField.charTyped(typedChar, keyCode); // テキストフィールドにキー入力を送信
        return super.charTyped(typedChar, keyCode);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.textField.mouseClicked(mouseX, mouseY, button);
        return super.mouseClicked(mouseX, mouseY, button);
    }


    public static void openCustomChatGUI(PlayerEntity player ,LMMMaidEntity target ) {
        Minecraft.getInstance().setScreen(new CustomChatGUI());
    }

}
