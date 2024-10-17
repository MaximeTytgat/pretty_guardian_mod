package com.max.prettyguardian.client.gui.components;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Whence;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Mth;
import net.minecraft.util.StringUtil;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class CustomMultilineTextField {
    public static final int NO_CHARACTER_LIMIT = Integer.MAX_VALUE;
    private static final int LINE_SEEK_PIXEL_BIAS = 2;
    private final Font font;
    private final List<CustomMultilineTextField.StringView> displayLines = Lists.newArrayList();
    private String value;
    private int cursor;
    private int selectCursor;
    private boolean selecting;
    private int characterLimit = Integer.MAX_VALUE;
    private final int width;
    private Consumer<String> valueListener = (p_239235_) -> {
    };
    private Runnable cursorListener = () -> {
    };

    public CustomMultilineTextField(Font p_239611_, int p_239612_) {
        this.font = p_239611_;
        this.width = p_239612_;
        this.setValue("");
    }

    public int characterLimit() {
        return this.characterLimit;
    }

    public void setCharacterLimit(int p_240163_) {
        if (p_240163_ < 0) {
            throw new IllegalArgumentException("Character limit cannot be negative");
        } else {
            this.characterLimit = p_240163_;
        }
    }

    public boolean hasCharacterLimit() {
        return this.characterLimit != Integer.MAX_VALUE;
    }

    public void setValueListener(Consumer<String> p_239920_) {
        this.valueListener = p_239920_;
    }

    public void setCursorListener(Runnable p_239258_) {
        this.cursorListener = p_239258_;
    }

    public void setValue(String p_239678_) {
        this.value = this.truncateFullText(p_239678_);
        this.cursor = this.value.length();
        this.selectCursor = this.cursor;
        this.onValueChange();
    }

    public String value() {
        return this.value;
    }

    public void insertText(String p_240016_) {
        if (!p_240016_.isEmpty() || this.hasSelection()) {
            String $$1 = this.truncateInsertionText(SharedConstants.filterText(p_240016_, true));
            CustomMultilineTextField.StringView $$2 = this.getSelected();
            this.value = (new StringBuilder(this.value)).replace($$2.beginIndex, $$2.endIndex, $$1).toString();
            this.cursor = $$2.beginIndex + $$1.length();
            this.selectCursor = this.cursor;
            this.onValueChange();
        }
    }

    public void deleteText(int p_239475_) {
        if (!this.hasSelection()) {
            this.selectCursor = Mth.clamp(this.cursor + p_239475_, 0, this.value.length());
        }

        this.insertText("");
    }

    public int cursor() {
        return this.cursor;
    }

    public void setSelecting(boolean p_239951_) {
        this.selecting = p_239951_;
    }

    public CustomMultilineTextField.StringView getSelected() {
        return new CustomMultilineTextField.StringView(Math.min(this.selectCursor, this.cursor), Math.max(this.selectCursor, this.cursor));
    }

    public int getLineCount() {
        return this.displayLines.size();
    }

    public int getLineAtCursor() {
        for(int $$0 = 0; $$0 < this.displayLines.size(); ++$$0) {
            CustomMultilineTextField.StringView $$1 = this.displayLines.get($$0);
            if (this.cursor >= $$1.beginIndex && this.cursor <= $$1.endIndex) {
                return $$0;
            }
        }

        return -1;
    }

    public CustomMultilineTextField.StringView getLineView(int p_239145_) {
        return this.displayLines.get(Mth.clamp(p_239145_, 0, this.displayLines.size() - 1));
    }

    public void seekCursor(Whence p_239798_, int p_239799_) {
        switch (p_239798_) {
            case ABSOLUTE:
                this.cursor = p_239799_;
                break;
            case RELATIVE:
                this.cursor += p_239799_;
                break;
            case END:
                this.cursor = this.value.length() + p_239799_;
        }

        this.cursor = Mth.clamp(this.cursor, 0, this.value.length());
        this.cursorListener.run();
        if (!this.selecting) {
            this.selectCursor = this.cursor;
        }

    }

    public void seekCursorLine(int p_239394_) {
        if (p_239394_ != 0) {
            int $$1 = this.font.width(this.value.substring(this.getCursorLineView().beginIndex, this.cursor)) + 2;
            CustomMultilineTextField.StringView $$2 = this.getCursorLineView(p_239394_);
            int $$3 = this.font.plainSubstrByWidth(this.value.substring($$2.beginIndex, $$2.endIndex), $$1).length();
            this.seekCursor(Whence.ABSOLUTE, $$2.beginIndex + $$3);
        }
    }

    public void seekCursorToPoint(double p_239579_, double p_239580_) {
        int $$2 = Mth.floor(p_239579_);
        Objects.requireNonNull(this.font);
        int $$3 = Mth.floor(p_239580_ / 9.0);
        CustomMultilineTextField.StringView $$4 = this.displayLines.get(Mth.clamp($$3, 0, this.displayLines.size() - 1));
        int $$5 = this.font.plainSubstrByWidth(this.value.substring($$4.beginIndex, $$4.endIndex), $$2).length();
        this.seekCursor(Whence.ABSOLUTE, $$4.beginIndex + $$5);
    }

    public boolean keyPressed(int p_239712_) {
        this.selecting = Screen.hasShiftDown();
        if (Screen.isSelectAll(p_239712_)) {
            this.cursor = this.value.length();
            this.selectCursor = 0;
            return true;
        } else if (Screen.isCopy(p_239712_)) {
            Minecraft.getInstance().keyboardHandler.setClipboard(this.getSelectedText());
            return true;
        } else if (Screen.isPaste(p_239712_)) {
            this.insertText(Minecraft.getInstance().keyboardHandler.getClipboard());
            return true;
        } else if (Screen.isCut(p_239712_)) {
            Minecraft.getInstance().keyboardHandler.setClipboard(this.getSelectedText());
            this.insertText("");
            return true;
        } else {
            CustomMultilineTextField.StringView $$1;
            switch (p_239712_) {
                case 257:
                case 335:
                    this.insertText("\n");
                    return true;
                case 259:
                    if (Screen.hasControlDown()) {
                        $$1 = this.getPreviousWord();
                        this.deleteText($$1.beginIndex - this.cursor);
                    } else {
                        this.deleteText(-1);
                    }

                    return true;
                case 261:
                    if (Screen.hasControlDown()) {
                        $$1 = this.getNextWord();
                        this.deleteText($$1.beginIndex - this.cursor);
                    } else {
                        this.deleteText(1);
                    }

                    return true;
                case 262:
                    if (Screen.hasControlDown()) {
                        $$1 = this.getNextWord();
                        this.seekCursor(Whence.ABSOLUTE, $$1.beginIndex);
                    } else {
                        this.seekCursor(Whence.RELATIVE, 1);
                    }

                    return true;
                case 263:
                    if (Screen.hasControlDown()) {
                        $$1 = this.getPreviousWord();
                        this.seekCursor(Whence.ABSOLUTE, $$1.beginIndex);
                    } else {
                        this.seekCursor(Whence.RELATIVE, -1);
                    }

                    return true;
                case 264:
                    if (!Screen.hasControlDown()) {
                        this.seekCursorLine(1);
                    }

                    return true;
                case 265:
                    if (!Screen.hasControlDown()) {
                        this.seekCursorLine(-1);
                    }

                    return true;
                case 266:
                    this.seekCursor(Whence.ABSOLUTE, 0);
                    return true;
                case 267:
                    this.seekCursor(Whence.END, 0);
                    return true;
                case 268:
                    if (Screen.hasControlDown()) {
                        this.seekCursor(Whence.ABSOLUTE, 0);
                    } else {
                        this.seekCursor(Whence.ABSOLUTE, this.getCursorLineView().beginIndex);
                    }

                    return true;
                case 269:
                    if (Screen.hasControlDown()) {
                        this.seekCursor(Whence.END, 0);
                    } else {
                        this.seekCursor(Whence.ABSOLUTE, this.getCursorLineView().endIndex);
                    }

                    return true;
                default:
                    return false;
            }
        }
    }

    public Iterable<CustomMultilineTextField.StringView> iterateLines() {
        return this.displayLines;
    }

    public boolean hasSelection() {
        return this.selectCursor != this.cursor;
    }

    @VisibleForTesting
    public String getSelectedText() {
        CustomMultilineTextField.StringView $$0 = this.getSelected();
        return this.value.substring($$0.beginIndex, $$0.endIndex);
    }

    private CustomMultilineTextField.StringView getCursorLineView() {
        return this.getCursorLineView(0);
    }

    private CustomMultilineTextField.StringView getCursorLineView(int p_239855_) {
        int $$1 = this.getLineAtCursor();
        if ($$1 < 0) {
            int var10002 = this.cursor;
            throw new IllegalStateException("Cursor is not within text (cursor = " + var10002 + ", length = " + this.value.length() + ")");
        } else {
            return this.displayLines.get(Mth.clamp($$1 + p_239855_, 0, this.displayLines.size() - 1));
        }
    }

    @VisibleForTesting
    public CustomMultilineTextField.StringView getPreviousWord() {
        if (this.value.isEmpty()) {
            return CustomMultilineTextField.StringView.EMPTY;
        } else {
            int $$0;
            for($$0 = Mth.clamp(this.cursor, 0, this.value.length() - 1); $$0 > 0 && Character.isWhitespace(this.value.charAt($$0 - 1)); --$$0) {
            }

            while($$0 > 0 && !Character.isWhitespace(this.value.charAt($$0 - 1))) {
                --$$0;
            }

            return new CustomMultilineTextField.StringView($$0, this.getWordEndPosition($$0));
        }
    }

    @VisibleForTesting
    public CustomMultilineTextField.StringView getNextWord() {
        if (this.value.isEmpty()) {
            return CustomMultilineTextField.StringView.EMPTY;
        } else {
            int $$0;
            for($$0 = Mth.clamp(this.cursor, 0, this.value.length() - 1); $$0 < this.value.length() && !Character.isWhitespace(this.value.charAt($$0)); ++$$0) {
            }

            while($$0 < this.value.length() && Character.isWhitespace(this.value.charAt($$0))) {
                ++$$0;
            }

            return new CustomMultilineTextField.StringView($$0, this.getWordEndPosition($$0));
        }
    }

    private int getWordEndPosition(int p_240093_) {
        int $$1;
        for($$1 = p_240093_; $$1 < this.value.length() && !Character.isWhitespace(this.value.charAt($$1)); ++$$1) {
        }

        return $$1;
    }

    private void onValueChange() {
        this.reflowDisplayLines();
        this.valueListener.accept(this.value);
        this.cursorListener.run();
    }

    private void reflowDisplayLines() {
        this.displayLines.clear();
        if (this.value.isEmpty()) {
            this.displayLines.add(CustomMultilineTextField.StringView.EMPTY);
        } else {
            this.font.getSplitter().splitLines(this.value, this.width, Style.EMPTY, false, (p_239846_, p_239847_, p_239848_) -> {
                this.displayLines.add(new CustomMultilineTextField.StringView(p_239847_, p_239848_));
            });
            if (this.value.charAt(this.value.length() - 1) == '\n') {
                this.displayLines.add(new CustomMultilineTextField.StringView(this.value.length(), this.value.length()));
            }

        }
    }

    private String truncateFullText(String p_239843_) {
        return this.hasCharacterLimit() ? StringUtil.truncateStringIfNecessary(p_239843_, this.characterLimit, false) : p_239843_;
    }

    private String truncateInsertionText(String p_239418_) {
        if (this.hasCharacterLimit()) {
            int $$1 = this.characterLimit - this.value.length();
            return StringUtil.truncateStringIfNecessary(p_239418_, $$1, false);
        } else {
            return p_239418_;
        }
    }

    @OnlyIn(Dist.CLIENT)
    protected record StringView(int beginIndex, int endIndex) {
        static final CustomMultilineTextField.StringView EMPTY = new CustomMultilineTextField.StringView(0, 0);

        private StringView(int beginIndex, int endIndex) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
        }

        public int beginIndex() {
            return this.beginIndex;
        }

        public int endIndex() {
            return this.endIndex;
        }
    }
}