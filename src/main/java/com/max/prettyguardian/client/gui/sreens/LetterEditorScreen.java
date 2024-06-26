package com.max.prettyguardian.client.gui.sreens;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.client.gui.components.CustomFittingMultiLineTextWidget;
import com.max.prettyguardian.client.gui.components.CustomMultiLineEditBox;
import com.max.prettyguardian.client.gui.components.CustomStringWidget;
import com.max.prettyguardian.client.gui.sreens.inventory.FakeLoveLetterMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LetterEditorScreen extends AbstractContainerScreen<FakeLoveLetterMenu> {
    private static final ResourceLocation LOVE_LETTER_LOCATION = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/gui/love_letter.png");
    private static final ResourceLocation LOVE_LETTER_LAYER_LOCATIION = new ResourceLocation(PrettyGuardian.MOD_ID, "textures/gui/love_letter_layer.png");
    private TextField textField;
    private CustomMultiLineEditBox output;
    private CustomFittingMultiLineTextWidget writtenOutput;
    private final ItemStack stack;
    private MutableComponent msg;
    private List<FormattedCharSequence> cachedPageComponents;

    public LetterEditorScreen(FakeLoveLetterMenu fakeMenu, Inventory inventory, Component component) {
        super(fakeMenu, inventory, Component.empty());
        this.cachedPageComponents = Collections.emptyList();
        this.stack = inventory.getSelected();

    }

    @Override
    protected void init() {
        int bookX = (this.width - 170) / 2;
        int bookY = 20;

        net.minecraft.client.gui.components.Button.Builder doneButton = net.minecraft.client.gui.components.Button.builder(
                CommonComponents.GUI_DONE,
                (button) -> this.onClose()
        ).bounds((this.width / 2) + 5, 196, 100, 20);

        net.minecraft.client.gui.components.Button.Builder signButton = net.minecraft.client.gui.components.Button.builder(
                Component.translatable("gui.sign_and_close"),
                (button) -> this.onClose("sign")
        ).bounds((this.width / 2) - 105, 196, 100, 20);


        this.output = new CustomMultiLineEditBox(
                this.font, bookX + 30, bookY + 18, 105, 115,
                Component.translatable("screen.prettyguardian.love_letter.placeholder").withStyle(Style.EMPTY.withColor(11828699)),
                Component.empty()
        );

        this.output.setMessage(Component.translatable("screen.prettyguardian.love_letter.placeholder").withStyle(Style.EMPTY.withColor(11828699)));

        if (this.stack.hasTag()) {
            CompoundTag tag = this.stack.getTag();

            if (tag != null && tag.contains("author")) {
                this.writtenOutput = new CustomFittingMultiLineTextWidget(
                        bookX + 30, bookY + 18, 105, 115,
                        Component.literal(tag.getString("msg")).withStyle(Style.EMPTY.withColor(11828699)),
                        this.font
                );

                this.addRenderableWidget(new CustomStringWidget(bookX + 22, 160, 100, 20,
                        Component.translatable("screen.prettyguardian.love_letter.send_by").withStyle(Style.EMPTY.withColor(10455011).applyFormats(ChatFormatting.BOLD))
                                .append(" ")
                                .append(tag.getString("author")),
                        this.font));

                this.addRenderableWidget(this.writtenOutput);
                this.addRenderableWidget(Button.builder(
                        CommonComponents.GUI_DONE,
                        (button) -> this.onClose()
                ).bounds((this.width / 2) - 100, 196, 200, 20).build());
            } else {
                if (tag != null && tag.contains("msg")) {
                    this.output.setValue(tag.getString("msg"));
                }
                this.addRenderableWidget(this.output);
                this.addRenderableWidget(doneButton.build());
                this.addRenderableWidget(signButton.build());
            }

        } else {
            this.addRenderableWidget(this.output);
            this.addRenderableWidget(doneButton.build());
            this.addRenderableWidget(signButton.build());
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics);
        int bookX = (this.width - 170) / 2;
        int bookY = 20;
        graphics.blit(LOVE_LETTER_LOCATION, bookX, bookY, 0, 0, 192, 192);

        int maxLines = Math.min(128 / 9, this.cachedPageComponents.size());
        for (int lineIndex = 0; lineIndex < maxLines; ++lineIndex) {
            FormattedCharSequence line = (FormattedCharSequence)this.cachedPageComponents.get(lineIndex);
            int lineY = 32 + lineIndex * 9;
            graphics.drawString(this.font, line, bookX + 36, lineY, 0, false);
        }

        Style hoveredComponentStyle = this.getClickedComponentStyleAt((double)mouseX, (double)mouseY);
        if (hoveredComponentStyle != null) {
            graphics.renderComponentHoverEffect(this.font, hoveredComponentStyle, mouseX, mouseY);
        }

        super.render(graphics, mouseX, mouseY, partialTicks);
        graphics.blit(LOVE_LETTER_LAYER_LOCATIION, bookX, bookY, 0, 0, 192, 192);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
//        this.renderBackground(guiGraphics);
    }

    @Nullable
    public Style getClickedComponentStyleAt(double p_98269_, double p_98270_) {
        int $$2 = Mth.floor(p_98269_ - (double)((this.width - 192) / 2) - 36.0);
        int $$3 = Mth.floor(p_98270_ - 2.0 - 30.0);
        if ($$2 >= 0 && $$3 >= 0) {
            Objects.requireNonNull(this.font);
            int $$4 = Math.min(128 / 9, this.cachedPageComponents.size());
            if ($$2 <= 114) {
                Objects.requireNonNull(this.minecraft.font);
                if ($$3 < 9 * $$4 + $$4) {
                    Objects.requireNonNull(this.minecraft.font);
                    int $$5 = $$3 / 9;
                    if ($$5 >= 0 && $$5 < this.cachedPageComponents.size()) {
                        FormattedCharSequence $$6 = (FormattedCharSequence)this.cachedPageComponents.get($$5);
                        return this.minecraft.font.getSplitter().componentStyleAtWidth($$6, $$2);
                    }

                    return null;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    @Override
    public void onClose() {
        if (this.stack.hasTag()) {
            CompoundTag tag = this.stack.getTag();
            if (tag != null && !tag.contains("author")) {
                tag.putString("msg", this.output.getValue());
            }
        } else {
            CompoundTag tag = new CompoundTag();
            tag.putString("msg", this.output.getValue());
            this.stack.setTag(tag);
        }

        super.onClose();
    }

    public void onClose(String action) {
        String playerName = Minecraft.getInstance().player.getName().getString();

        if (Objects.equals(action, "sign")) {
            if (this.stack.hasTag()) {
                CompoundTag tag = this.stack.getTag();
                if (tag != null) {
                    tag.putString("msg", this.output.getValue());
                    tag.putString("author", playerName);
                    tag.putString("Sign", "true");
                }
            } else {
                CompoundTag tag = new CompoundTag();
                tag.putString("msg", this.output.getValue());
                tag.putString("author", playerName);
                tag.putString("Sign", "true");
                this.stack.setTag(tag);
            }
        }

        super.onClose();
    }

}