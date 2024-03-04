package com.max.prettyguardian.item.custom;

import com.max.prettyguardian.PrettyGuardian;
import com.max.prettyguardian.client.gui.components.CustomFittingMultiLineTextWidget;
import com.max.prettyguardian.client.gui.components.CustomMultiLineEditBox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LoveLetterItem extends Item  {
    public LoveLetterItem(Properties properties) {
        super(properties);
    }
    public static final ResourceLocation BOOK_LOCATION = new ResourceLocation("textures/gui/book.png");

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (level.isClientSide) {
            Minecraft.getInstance().setScreen(new LetterEditorScreen(player.getItemInHand(interactionHand)));
        }

        return super.use(level, player, interactionHand);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return itemStack.hasTag() && itemStack.getTag().contains("author");
    }


    private void openLetterEditor(ItemStack stack) {
    }

    private static class LetterEditorScreen extends Screen {
        private TextField textField;
        private CustomMultiLineEditBox output;
        private CustomFittingMultiLineTextWidget writtenOutput;
        private final ItemStack stack;
        private MutableComponent msg;
        private List<FormattedCharSequence> cachedPageComponents;

        protected LetterEditorScreen(ItemStack stack) {
            super(Component.translatable("screen.prettyguardian.love_letter.title"));
            this.cachedPageComponents = Collections.emptyList();
            this.stack = stack;
        }

        @Override
        protected void init() {
            int bookX = (this.width - 192) / 2;
            int bookY = 2;

            Button.Builder doneButton = Button.builder(
                    CommonComponents.GUI_DONE,
                    (button) -> this.onClose()
            ).bounds((this.width / 2) + 5, 196, 100, 20);

            Button.Builder signButton = Button.builder(
                    Component.translatable("gui.sign_and_close"),
                    (button) -> this.onClose("sign")
            ).bounds((this.width / 2) - 105, 196, 100, 20);


            this.output = new CustomMultiLineEditBox(
                    this.font, bookX + 30, bookY + 18, 125, 150,
                    Component.translatable("screen.prettyguardian.love_letter.placeholder"),
                    Component.translatable("item.prettyguardian.msg")
            );

            if (this.stack.hasTag()) {
                CompoundTag tag = this.stack.getTag();

                if (tag != null && tag.contains("author")) {
                    this.writtenOutput = new CustomFittingMultiLineTextWidget(
                            bookX + 30, bookY + 18, 125, 150,
                            Component.literal(tag.getString("msg")).withStyle(Style.EMPTY.withColor(3539535)),
                            this.font
                    );

                    this.addRenderableWidget(new StringWidget((this.width / 2), 150, 200, 20,
                            Component.translatable("screen.prettyguardian.love_letter.send_by")
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

        public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
            this.renderBackground(graphics);
            int bookX = (this.width - 192) / 2;
            int bookY = 2;
            graphics.blit(BOOK_LOCATION, bookX, bookY, 0, 0, 192, 192);

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
            PrettyGuardian.LOGGER.info("Closing " + this.output.getValue());

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
            PrettyGuardian.LOGGER.info("Signing and closing " + this.output.getValue());
            String playerName = Minecraft.getInstance().player.getName().getString();

            if (Objects.equals(action, "sign")) {
                if (this.stack.hasTag()) {
                    CompoundTag tag = this.stack.getTag();
                    if (tag != null) {
                        tag.putString("msg", this.output.getValue());
                        tag.putString("author", playerName);
                    }
                } else {
                    CompoundTag tag = new CompoundTag();
                    tag.putString("msg", this.output.getValue());
                    tag.putString("author", playerName);
                    this.stack.setTag(tag);
                }
            }

            super.onClose();
        }

    }
}