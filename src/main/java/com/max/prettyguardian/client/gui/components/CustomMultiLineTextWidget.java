package com.max.prettyguardian.client.gui.components;

import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractStringWidget;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.network.chat.Component;
import net.minecraft.util.SingleKeyCache;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;
import java.util.OptionalInt;

public class CustomMultiLineTextWidget extends AbstractStringWidget {
    private OptionalInt maxWidth;
    private OptionalInt maxRows;
    private final SingleKeyCache<CustomMultiLineTextWidget.CacheKey, MultiLineLabel> cache;
    private boolean centered;

    public CustomMultiLineTextWidget(Component p_270532_, Font p_270639_) {
        this(0, 0, p_270532_, p_270639_);
    }

    public CustomMultiLineTextWidget(int p_270325_, int p_270355_, Component p_270069_, Font p_270673_) {
        super(p_270325_, p_270355_, 0, 0, p_270069_, p_270673_);
        this.maxWidth = OptionalInt.empty();
        this.maxRows = OptionalInt.empty();
        this.centered = false;
        this.cache = Util.singleKeyCache((p_270516_) -> {
            return p_270516_.maxRows.isPresent() ? MultiLineLabel.create(p_270673_, p_270516_.message, p_270516_.maxWidth, p_270516_.maxRows.getAsInt()) : MultiLineLabel.create(p_270673_, p_270516_.message, p_270516_.maxWidth);
        });
        this.active = false;
    }

    public CustomMultiLineTextWidget setColor(int p_270378_) {
        super.setColor(p_270378_);
        return this;
    }

    public CustomMultiLineTextWidget setMaxWidth(int p_270776_) {
        this.maxWidth = OptionalInt.of(p_270776_);
        return this;
    }

    public CustomMultiLineTextWidget setMaxRows(int p_270085_) {
        this.maxRows = OptionalInt.of(p_270085_);
        return this;
    }

    public CustomMultiLineTextWidget setCentered(boolean p_270493_) {
        this.centered = p_270493_;
        return this;
    }

    public int getWidth() {
        return ((MultiLineLabel)this.cache.getValue(this.getFreshCacheKey())).getWidth();
    }

    public int getHeight() {
        int var10000 = ((MultiLineLabel)this.cache.getValue(this.getFreshCacheKey())).getLineCount();
        Objects.requireNonNull(this.getFont());
        return var10000 * 9;
    }

    public void renderWidget(GuiGraphics p_282535_, int p_261774_, int p_261640_, float p_261514_) {
        MultiLineLabel $$4 = (MultiLineLabel)this.cache.getValue(this.getFreshCacheKey());
        int $$5 = this.getX();
        int $$6 = this.getY();
        Objects.requireNonNull(this.getFont());
        int $$7 = 9;
        int $$8 = this.getColor();
        if (this.centered) {
            $$4.renderCentered(p_282535_, $$5 + this.getWidth() / 2, $$6, $$7, $$8);
        } else {
            $$4.renderLeftAlignedNoShadow(p_282535_, $$5, $$6, $$7, $$8);
        }

    }

    private CustomMultiLineTextWidget.CacheKey getFreshCacheKey() {
        return new CustomMultiLineTextWidget.CacheKey(this.getMessage(), this.maxWidth.orElse(Integer.MAX_VALUE), this.maxRows);
    }

    @OnlyIn(Dist.CLIENT)
    static record CacheKey(Component message, int maxWidth, OptionalInt maxRows) {
        CacheKey(Component message, int maxWidth, OptionalInt maxRows) {
            this.message = message;
            this.maxWidth = maxWidth;
            this.maxRows = maxRows;
        }

        public Component message() {
            return this.message;
        }

        public int maxWidth() {
            return this.maxWidth;
        }

        public OptionalInt maxRows() {
            return this.maxRows;
        }
    }
}