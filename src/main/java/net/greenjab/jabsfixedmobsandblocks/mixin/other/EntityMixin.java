package net.greenjab.jabsfixedmobsandblocks.mixin.other;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @ModifyVariable(method = "refreshDimensions", at = @At(value = "STORE"), ordinal = 1)
    private EntityDimensions fakeItemHitboxSize(EntityDimensions newDim){
        Entity E = (Entity)(Object)this;
        if (E instanceof ItemEntity) {
            Entity e2 = E.getFirstPassenger();
            if (e2!=null) {
                EntityDimensions ed = e2.getDimensions(e2.getPose());
                return EntityDimensions.fixed(ed.width(), 0.1f);
            }
        }
        return newDim;
    }
}
