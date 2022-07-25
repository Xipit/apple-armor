// Made with Blockbench 4.2.4
	// Exported for Minecraft version 1.17
	// Paste this class into your mod and generate all required imports
	
	package com.example.mod;
	
	public class apple_armor_helmet extends SinglePartEntityModel<Entity> {
		private final ModelPart root;
	
		private final ModelPart helmet;
	
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
		.cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)),
				ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
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