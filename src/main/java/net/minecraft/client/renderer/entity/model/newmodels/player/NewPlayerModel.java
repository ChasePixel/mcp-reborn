package net.minecraft.client.renderer.entity.model.newmodels.player;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.newmodels.HumanoidModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.HandSide;
import net.minecraft.util.random.RandomSource;

import java.util.List;

public class NewPlayerModel<T extends LivingEntity>
extends HumanoidModel<T> {
    private static final String EAR = "ear";
    private static final String CLOAK = "cloak";
    private static final String LEFT_SLEEVE = "left_sleeve";
    private static final String RIGHT_SLEEVE = "right_sleeve";
    private static final String LEFT_PANTS = "left_pants";
    private static final String RIGHT_PANTS = "right_pants";
    private final List<ModelPart> parts;
    public final ModelPart leftSleeve;
    public final ModelPart rightSleeve;
    public final ModelPart leftPants;
    public final ModelPart rightPants;
    public final ModelPart jacket;
    private final ModelPart cloak;
    private final ModelPart ear;
    private final boolean slim;

    public NewPlayerModel(ModelPart modelPart2, boolean bl) {
        super(modelPart2, RenderType::entityTranslucent);
        this.slim = bl;
        this.ear = modelPart2.getChild(EAR);
        this.cloak = modelPart2.getChild(CLOAK);
        this.leftSleeve = modelPart2.getChild(LEFT_SLEEVE);
        this.rightSleeve = modelPart2.getChild(RIGHT_SLEEVE);
        this.leftPants = modelPart2.getChild(LEFT_PANTS);
        this.rightPants = modelPart2.getChild(RIGHT_PANTS);
        this.jacket = modelPart2.getChild("jacket");
        this.parts = modelPart2.getAllParts().filter(modelPart -> !modelPart.isEmpty()).collect(ImmutableList.toImmutableList());
    }

    public static MeshDefinition createMesh(CubeDeformation cubeDeformation, boolean bl) {
        MeshDefinition meshDefinition = HumanoidModel.createMesh(cubeDeformation, 0.0f);
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild(EAR, CubeListBuilder.create().texOffs(24, 0).addBox(-3.0f, -6.0f, -1.0f, 6.0f, 6.0f, 1.0f, cubeDeformation), PartPose.ZERO);
        partDefinition.addOrReplaceChild(CLOAK, CubeListBuilder.create().texOffs(0, 0).addBox(-5.0f, 0.0f, -1.0f, 10.0f, 16.0f, 1.0f, cubeDeformation, 1.0f, 0.5f), PartPose.offset(0.0f, 0.0f, 0.0f));
        float f = 0.25f;
        if (bl) {
            partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0f, -2.0f, -2.0f, 3.0f, 12.0f, 4.0f, cubeDeformation), PartPose.offset(5.0f, 2.5f, 0.0f));
            partDefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0f, -2.0f, -2.0f, 3.0f, 12.0f, 4.0f, cubeDeformation), PartPose.offset(-5.0f, 2.5f, 0.0f));
            partDefinition.addOrReplaceChild(LEFT_SLEEVE, CubeListBuilder.create().texOffs(48, 48).addBox(-1.0f, -2.0f, -2.0f, 3.0f, 12.0f, 4.0f, cubeDeformation.extend(0.25f)), PartPose.offset(5.0f, 2.5f, 0.0f));
            partDefinition.addOrReplaceChild(RIGHT_SLEEVE, CubeListBuilder.create().texOffs(40, 32).addBox(-2.0f, -2.0f, -2.0f, 3.0f, 12.0f, 4.0f, cubeDeformation.extend(0.25f)), PartPose.offset(-5.0f, 2.5f, 0.0f));
        } else {
            partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, cubeDeformation), PartPose.offset(5.0f, 2.0f, 0.0f));
            partDefinition.addOrReplaceChild(LEFT_SLEEVE, CubeListBuilder.create().texOffs(48, 48).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, cubeDeformation.extend(0.25f)), PartPose.offset(5.0f, 2.0f, 0.0f));
            partDefinition.addOrReplaceChild(RIGHT_SLEEVE, CubeListBuilder.create().texOffs(40, 32).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, cubeDeformation.extend(0.25f)), PartPose.offset(-5.0f, 2.0f, 0.0f));
        }
        partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, cubeDeformation), PartPose.offset(1.9f, 12.0f, 0.0f));
        partDefinition.addOrReplaceChild(LEFT_PANTS, CubeListBuilder.create().texOffs(0, 48).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, cubeDeformation.extend(0.25f)), PartPose.offset(1.9f, 12.0f, 0.0f));
        partDefinition.addOrReplaceChild(RIGHT_PANTS, CubeListBuilder.create().texOffs(0, 32).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, cubeDeformation.extend(0.25f)), PartPose.offset(-1.9f, 12.0f, 0.0f));
        partDefinition.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(16, 32).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, cubeDeformation.extend(0.25f)), PartPose.ZERO);
        return meshDefinition;
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return Iterables.concat(super.bodyParts(), ImmutableList.of(this.leftPants, this.rightPants, this.leftSleeve, this.rightSleeve, this.jacket));
    }

    public void renderEars(MatrixStack poseStack, IVertexBuilder vertexConsumer, int n, int n2) {
        this.ear.copyFrom(this.head);
        this.ear.x = 0.0f;
        this.ear.y = 0.0f;
        this.ear.render(poseStack, vertexConsumer, n, n2);
    }

    public void renderCloak(MatrixStack poseStack, IVertexBuilder vertexConsumer, int n, int n2) {
        this.cloak.render(poseStack, vertexConsumer, n, n2);
    }

    @Override
    public void setupAnim(T t, float f, float f2, float f3, float f4, float f5) {
        super.setupAnim(t, f, f2, f3, f4, f5);
        this.leftPants.copyFrom(this.leftLeg);
        this.rightPants.copyFrom(this.rightLeg);
        this.leftSleeve.copyFrom(this.leftArm);
        this.rightSleeve.copyFrom(this.rightArm);
        this.jacket.copyFrom(this.body);
        if (((LivingEntity)t).getItemBySlot(EquipmentSlotType.CHEST).isEmpty()) {
            if (((Entity)t).isCrouching()) {
                this.cloak.z = 1.4f;
                this.cloak.y = 1.85f;
            } else {
                this.cloak.z = 0.0f;
                this.cloak.y = 0.0f;
            }
        } else if (((Entity)t).isCrouching()) {
            this.cloak.z = 0.3f;
            this.cloak.y = 0.8f;
        } else {
            this.cloak.z = -1.1f;
            this.cloak.y = -0.85f;
        }
    }

    @Override
    public void setAllVisible(boolean bl) {
        super.setAllVisible(bl);
        this.leftSleeve.visible = bl;
        this.rightSleeve.visible = bl;
        this.leftPants.visible = bl;
        this.rightPants.visible = bl;
        this.jacket.visible = bl;
        this.cloak.visible = bl;
        this.ear.visible = bl;
    }

    @Override
    public void translateToHand(HandSide humanoidArm, MatrixStack poseStack) {
        ModelPart modelPart = this.getArm(humanoidArm);
        if (this.slim) {
            float f = 0.5f * (float)(humanoidArm == HandSide.RIGHT ? 1 : -1);
            modelPart.x += f;
            modelPart.translateAndRotate(poseStack);
            modelPart.x -= f;
        } else {
            modelPart.translateAndRotate(poseStack);
        }
    }

    public ModelPart getRandomModelPart(RandomSource randomSource) {
        return this.parts.get(randomSource.nextInt(this.parts.size()));
    }
}

