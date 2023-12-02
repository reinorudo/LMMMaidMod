package com.LMMMaid.LMMMaidMod.chat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.LMMMaid.LMMMaidMod.entity.LMMMaidEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

public class CustomChatGUI extends Screen {
    private TextFieldWidget textField;
    private List<String> maidChatHistory;
    private List<String> playerChatHistory;
    private int scrollOffset;
    private int visibleLines;

    public CustomChatGUI() {
        super(new StringTextComponent("Custom Chat"));
        this.playerChatHistory = new ArrayList<>();
        this.maidChatHistory = new ArrayList<>();
        this.scrollOffset = 0;
        this.visibleLines = 100;//最大表示行数
    }

    @Override
    protected void init() {
        this.textField = new TextFieldWidget(this.font, this.width / 2 - 100, this.height - 30, 200, 20, new StringTextComponent(""));
        this.addButton(new Button(this.width / 2 + 104, this.height - 31, 50, 20, new StringTextComponent("Send"), button -> this.sendMessagePlayer()));
        this.textField.setFocus(true);

        int closeButtonWidth = 20;
        int closeButtonHeight = 20;
        int closeButtonX = this.width - closeButtonWidth - 5; // 右端から5pxの位置
        int closeButtonY = 5; // 上端から5pxの位置

        this.addButton(new Button(closeButtonX, closeButtonY, closeButtonWidth, closeButtonHeight, new StringTextComponent("X"), button -> this.onClose()));

    }

    private List<String> combineChat() {
        List<String> combinedChat = new ArrayList<>();
        int maxLength = Math.max(playerChatHistory.size(), maidChatHistory.size());
    
        for (int i = 0; i < maxLength; i++) {
            if (i < maidChatHistory.size()) {
                combinedChat.add("MAID : " + maidChatHistory.get(i));
            }
            if (i < playerChatHistory.size()) {
                combinedChat.add("YOU : " + playerChatHistory.get(i));
            }
        }
    
        this.scrollOffset = 0; // スクロールをリセットして最下部に表示
    
        return combinedChat;
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
    public void render(MatrixStack matrixStack,int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        List<String> combinedChat = combineChat();
        int chatY = this.height - 50;
        int startLine = Math.max(0, combinedChat.size() - this.visibleLines - this.scrollOffset);
        int endLine = Math.min(combinedChat.size(), startLine + this.visibleLines);

        for (int i = startLine; i < endLine; i++) {
            String message = combinedChat.get(i);
            int bgColor = i % 2 == 0 ? 0x00FF00 : 0x888888;// デフォルトの背景色

            // メイドの発言をグレーの背景色に変更
            if (message.startsWith("MAID : ")) {
                bgColor = 0x888888; // グレーの背景色
            } else if (message.startsWith("YOU : ")) {
                bgColor = 0x00FF00; // 自分の発言を緑の背景色に変更
            }

            int wrapWidth = this.width - 16; // 折り返しの幅

            if (this.font.width(message) > wrapWidth) {
                List<String> lines = new ArrayList<>();
                int lastIndex = 0;
                int lastWrapIndex = 0;
                while (lastIndex < message.length()) {
                    if (this.font.width(message.substring(lastWrapIndex, lastIndex)) > wrapWidth) {
                        String line = message.substring(lastWrapIndex, lastIndex);
                        lines.add(line);
                        lastWrapIndex = lastIndex;
                    }
                    lastIndex++;
                }
                if (lastWrapIndex < message.length()) {
                    String lastLine = message.substring(lastWrapIndex);
                    lines.add(lastLine);
                }
            
                // Reverse the lines and render
                Collections.reverse(lines);
                for (String line : lines) {
                    AbstractGui.fill(matrixStack, 8, chatY - this.font.lineHeight - 2, this.width - 8, chatY + 2, bgColor);
                    this.font.drawShadow(matrixStack, line, 10, chatY, 0xFFFFFF);
                    chatY -= this.font.lineHeight;
                }
            } else {
                AbstractGui.fill(matrixStack, 8, chatY - this.font.lineHeight - 2, this.width - 8, chatY + 2, bgColor);
                this.font.drawShadow(matrixStack, message, 10, chatY, 0xFFFFFF);
                chatY -= this.font.lineHeight;
            }
        }
        chatY -= 10; // プレイヤーのチャットと区切るために少し空白を作る


        this.textField.render(matrixStack,mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        int totalLines = this.playerChatHistory.size() + this.maidChatHistory.size();
        int maxVisibleLines = this.visibleLines;
    
        if (totalLines > maxVisibleLines) {
            int scrollAmount = (int) delta;
            this.scrollOffset = Math.max(0, Math.min(totalLines - maxVisibleLines, this.scrollOffset - scrollAmount));
        }
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
