package net.greenjab.jabsfixedmobsandblocks.client.mixin.villager;

import com.llamalad7.mixinextras.sugar.Local;
import net.greenjab.jabsfixedmobsandblocks.client.JabsFixedMobsAndBlocksClient;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
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

    @Redirect(method = "extractLabels", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/network/chat/Component;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;"
    ))
    private MutableComponent reverseProfessionSkillTitle(String key, Object[] args) {
        MerchantScreen MS = (MerchantScreen) (Object)this;
        int traderLevel = MS.getMenu().getTraderLevel();
        if (MS.getTitle().getContents() instanceof TranslatableContents)
            return Component.translatable("merchant.title", Component.translatable("merchant.level." + traderLevel), MS.getTitle());
        return (MutableComponent) MS.getTitle();
    }
}
