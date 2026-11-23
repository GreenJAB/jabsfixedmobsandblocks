package net.greenjab.jabsfixedmobsandblocks.client.mixin.villager;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.npc.BabyVillagerModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BabyVillagerModel.class)
public abstract class BabyVillagerModelMixin {

    @Inject(method = "createBodyModel", at = @At(value = "HEAD"), cancellable = true)
    private static void betterBabyVillager(CallbackInfoReturnable<MeshDefinition> cir){
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition arms = root.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 17.5F, 0.0F));
        arms.addOrReplaceChild(
                "right_hand",
                CubeListBuilder.create()
                        .texOffs(36, 16)
                        .addBox(-1.0F, -2.4925F, -1.8401F, 2.0F, 4.0F, 2.0F)
                        .texOffs(16, 16)
                        .addBox(5.0F, -2.4925F, -1.8401F, 2.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(-3.0F, 1.4025F, -0.9599F, -1.0472F, 0.0F, 0.0F)
        );
        arms.addOrReplaceChild(
                "middlearm_r1",
                CubeListBuilder.create().texOffs(24, 18).addBox(-2.0F, -0.9924F, -0.9825F, 4.0F, 2.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 0.9024F, -1.8175F, -1.0472F, 0.0F, 0.0F)
        );
        root.addOrReplaceChild(
                "right_leg", CubeListBuilder.create().texOffs(8, 24).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F), PartPose.offset(-1.0F, 21.5F, 0.0F)
        );
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 24).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F), PartPose.offset(1.0F, 21.5F, 0.0F));
        PartDefinition head = root.addOrReplaceChild(
                "head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -9.0F, -3.5F, 8.0F, 9.0F, 7.0F), PartPose.offset(0.0F, 16.0F, 0.0F)
        );
        head.addOrReplaceChild(
                "hat", CubeListBuilder.create().texOffs(0, 31).addBox(-4.0F, -5.0F, -3.5F, 8.0F, 9.0F, 7.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, -4.0F, 0.0F)
        );
        head.addOrReplaceChild("hat_rim", CubeListBuilder.create().texOffs(0, 46).addBox(-7.0F, -0.5F, -6.0F, 14.0F, 1.0F, 12.0F), PartPose.offset(0.0F, -4.5F, 0.0F));
        head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(23, 0).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 3.0F, 1.0F), PartPose.offset(0.0F, -2.0F, -4.0F));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -2.75F, -1.5F, 4.0F, 5.0F, 3.0F), PartPose.offset(0.0F, 18.75F, 0.0F));
        root.addOrReplaceChild(
                "bb_main",
                CubeListBuilder.create().texOffs(16, 22).addBox(-2.5F, -8.0F, -1.5F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.2F)),
                PartPose.offset(0.5F, 24.0F, 0.0F)
        );
        cir.setReturnValue(mesh);
    }
}
