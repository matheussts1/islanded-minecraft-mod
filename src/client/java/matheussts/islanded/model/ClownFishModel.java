package matheussts.islanded.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import matheussts.islanded.render.clownfish.ClownFishRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;


public class ClownFishModel <T extends ClownFishRenderState> extends EntityModel<T> {

    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart left_fin;
    private final ModelPart right_fin;

    public ClownFishModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.tail = this.body.getChild("tail");
        this.left_fin = root.getChild("left_fin");
        this.right_fin = root.getChild("right_fin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -9.0F, -3.0F, 7.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(10, 13).addBox(-5.0F, -7.5F, -2.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(10, 9).addBox(-3.0F, -11.0F, -1.0F, 7.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(10, 11).addBox(-3.0F, -4.0F, -1.0F, 7.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 9).addBox(4.0F, -10.0F, -1.0F, 5.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_fin = partdefinition.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = left_fin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -2.0F, 1.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.25F, -4.25F, 0.0F, 0.48F, 0.0F));

        PartDefinition right_fin = partdefinition.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 4.0F));

        PartDefinition cube_r2 = right_fin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(4, 16).addBox(-2.0F, -2.0F, 1.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -5.25F, -2.75F, 0.0F, -0.6545F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(ClownFishRenderState renderState) {
        super.setupAnim((T) renderState);

        float speed = 1.0F;  // velocidade do ciclo
        float t = renderState.ageInTicks * speed * 0.1F; // * 0.1 pra suavizar

        // Corpo: balanço lateral suave
        this.body.yRot = (float) Math.sin(t) * 0.15F; // ~8 graus

        // Cauda: fase oposta, amplitude maior
        this.tail.yRot = (float) Math.sin(t + Math.PI) * 0.05F; // ~20 graus

        // Nadadeiras alternadas
        this.left_fin.zRot = (float) Math.sin(t) * 0.10F;
        this.right_fin.zRot = (float) Math.sin(t + Math.PI) * 0.10F;
    }

    public void renderBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
        body.render(poseStack, buffer, packedLight, packedOverlay);
        left_fin.render(poseStack, buffer, packedLight, packedOverlay);
        right_fin.render(poseStack, buffer, packedLight, packedOverlay);
    }
}
