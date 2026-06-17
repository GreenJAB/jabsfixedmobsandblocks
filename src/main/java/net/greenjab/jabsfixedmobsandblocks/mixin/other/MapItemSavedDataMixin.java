package net.greenjab.jabsfixedmobsandblocks.mixin.other;

import net.minecraft.world.level.saveddata.maps.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.*;

@Mixin(MapItemSavedData.class)
public abstract class MapItemSavedDataMixin {

    @Redirect(method = "checkBanners", at = @At(value = "INVOKE",
                                                target = "Lnet/minecraft/world/level/saveddata/maps/MapBanner;equals(Ljava/lang/Object;)Z"))
    private boolean noRemoveCustomIconBanner(MapBanner instance, Object o){
        if (instance.pos().getY() == -32768) return true;
        if (instance.pos().getY() <= -1000) return false;
        return instance.equals(o);
    }
}
