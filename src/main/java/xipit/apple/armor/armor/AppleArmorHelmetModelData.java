package xipit.apple.armor.armor;

import net.minecraft.client.model.*;

public class AppleArmorHelmetModelData {
    public static ModelData getModelData(){
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        var head = root.addChild(
                "head",
                ModelPartBuilder.create(),
                ModelTransform.NONE);

        head.addChild(
                "helmet",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .mirrored(false)
                        .cuboid(-4.0F, -40.0F + 8F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.75F)),
                ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        head.addChild(
                "apple_twig",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .mirrored(false)
                        .cuboid(-1.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(0.5F, -41.5F + 31.5F, 0.0F, 0.0F, 0.0F, -0.2182F)
        );

        head.addChild(
                "apple_leaf",
                ModelPartBuilder.create()
                        .uv(0, 16)
                        .mirrored(false)
                        .cuboid(-1.0F, -1.7F, -1.35F, 5.0F, 3.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -42.0F + 31.5F, 0.0F,2.2234F, 0.1623F, -0.546F)
        );



        return data;
    }
}
