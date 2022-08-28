// Made with Blockbench 4.3.0
	// Exported for Minecraft version 1.17
	// Paste this class into your mod and generate all required imports
	
	package com.example.mod;
	
	public class apple_armor_helmet extends SinglePartEntityModel<Entity> {
		private final ModelPart root;
	
		private final ModelPart helmet;
	private final ModelPart bb_main;
	private final ModelPart apple_twig_r1;
	private final ModelPart apple_leaf_r1;
	
		public apple_armor_helmet(ModelPart root) {
this.root = root;
	
// TODO define your model parts here - 'this.body = root.getChild("body");' etc
		}
	
		public static TexturedModelData getTexturedModelData() {
// TODO replace 'undefined' with 'root'
	
ModelData data = new ModelData();
ModelPartData root = data.getRoot();
	
ModelPartData helmet = undefined.addChild(
				"helmet",
				ModelPartBuilder.create()
		.uv(0, 0)
		.mirrored(false)
		.cuboid(-4.0F, -40.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)),
				ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
			);

		ModelPartData bb_main = undefined.addChild(
				"bb_main",
				ModelPartBuilder.create()
		,
				ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
			);

		ModelPartData apple_twig_r1 = bb_main.addChild(
				"apple_twig_r1",
				ModelPartBuilder.create()
		.uv(0, 0)
		.mirrored(false)
		.cuboid(-1.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)),
				ModelTransform.of(0.5F, -41.5F, 0.0F, 0.0F, 0.0F, -0.2182F)
			);

		ModelPartData apple_leaf_r1 = bb_main.addChild(
				"apple_leaf_r1",
				ModelPartBuilder.create()
		.uv(0, 16)
		.mirrored(false)
		.cuboid(-1.0F, -1.7F, -1.35F, 5.0F, 3.0F, 1.0F, new Dilation(0.0F)),
				ModelTransform.of(0.0F, -42.0F, 0.0F, 2.2234F, 0.1623F, -0.546F)
			);
	
return TexturedModelData.of(data, 32, 32);
		}
	
		@Override
		public void setAngles(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
//previously the render function, render code was moved to a method below
		}
	
		@Override
		public ModelPart getPart() {
return this.root;
		}
	
		
	}