package net.greenjab.jabsfixedmobsandblocks.mixin.other;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.saveddata.maps.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import java.util.*;

@Mixin(MapItemSavedData.class)
public abstract class MapItemSavedDataMixin {

    @ModifyExpressionValue(method = "checkBanners", at = @At(value = "INVOKE",
                                                target = "Lnet/minecraft/world/level/saveddata/maps/MapBanner;equals(Ljava/lang/Object;)Z"))
    private boolean noRemoveCustomIconBanner(boolean original, @Local(ordinal = 1) MapBanner current){
        if (current!=null) {
            if (current.pos().getY() == -32768) return true;
            if (current.pos().getY() <= -1000) return false;
        }
        return original;
    }
}
