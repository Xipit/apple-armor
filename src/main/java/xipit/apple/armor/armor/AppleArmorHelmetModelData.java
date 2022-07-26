package xipit.apple.armor.armor;

import net.minecraft.client.model.*;

public class AppleArmorHelmetModelData {
    public static ModelData getModelData(){
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        root.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE);

        var head = root.addChild(
                "head",
                ModelPartBuilder.create(),
                ModelTransform.NONE);

        //TODO: customize helmet
        head.addChild(
                "helmet",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .mirrored(false)
                        .cuboid(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(1F)),
                ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );


        return data;
    }
}
