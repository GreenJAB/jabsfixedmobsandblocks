package net.greenjab.jabsfixedmobsandblocks.client.mixin.villager;

import com.llamalad7.mixinextras.sugar.Local;
import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.greenjab.jabsfixedmobsandblocks.client.JabsFixedMobsAndBlocksClient;
import net.greenjab.jabsfixedmobsandblocks.client.VillagerParticle;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MerchantScreen.class)
public abstract class MerchantScreenMixin {
    @Unique
    private static final Identifier CHEST_SLOTS_TEXTURE = Identifier.parse("container/horse/chest_slots");

    @Inject(method = "extractBackground", at = @At(value = "TAIL"))
    private void armorSlotBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci,
                                     @Local(ordinal = 2) int xo,
                                     @Local(ordinal = 3) int yo) {
        MerchantScreen MS = (MerchantScreen) (Object)this;
        int traderLevel = MS.getMenu().getTraderLevel();
        int yOff = JabsFixedMobsAndBlocksClient.usingCustomContainers() ? -6:0;
        for (int k = 5 - traderLevel; k < 4; k++) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, CHEST_SLOTS_TEXTURE, 90, 54, 0, 0, xo + 250 - 1 + 2, yo + 8 + k * 18 - 1 +yOff, 18, 18);
        }
    }

    @Inject(method = "extractBackground", at = @At(value = "TAIL"))
    private void villagerWant(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci,
                              @Local(ordinal = 2) int xo,
                              @Local(ordinal = 3) int yo) {
        if (VillagerParticle.lastNeed != null) {
            int x = 278;
            int y = 1;
            if (JabsFixedMobsAndBlocksClient.usingCustomContainers()) {
                x = 102;
                y = 14;
            }
            graphics.blit(RenderPipelines.GUI_TEXTURED, JabsFixedMobsAndBlocks.id("textures/gui/sprites/villager_want.png"),
                    xo + x, yo + y, 0, 0, 24, 24, 24, 24, ARGB.white(1F));
            graphics.blit(RenderPipelines.GUI_TEXTURED, Identifier.parse("textures/particle/" + VillagerParticle.lastNeed +".png"),
                    xo + x + 2, yo + y + 2, 0, 0, 16, 16, 16, 16, ARGB.white(1F));
        }
    }
}
