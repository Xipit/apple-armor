// Made with Blockbench 4.2.4
	// Exported for Minecraft version 1.17 Mojmap
	// Paste this class into your mod and generate all required imports
	
	package com.example.mod;
	
	public class custom_model extends HierarchicalModel<Entity> {
		private final ModelPart root;
	
		private final ModelPart bb_main;
	
		public custom_model(ModelPart root) {
this.root = root;
	
// TODO define your model parts here - 'this.body = root.getChild("body");' etc
		}
	
		public static LayerDefinition getTexturedModelData() {
// TODO replace 'undefined' with 'root'
	
MeshDefinition data = new MeshDefinition();
PartDefinition root = data.getRoot();
	
PartDefinition bb_main = undefined.addOrReplaceChild(
			"bb_main",
			CubeListBuilder.create()
			.texOffs(0, 0)
			.mirror(false)
			.addBox(-6.0F, -10.0F, 0.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
			PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F)
			);
	
return LayerDefinition.create(data, 32, 32);
		}
	
		@Override
		public void setupAnim(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
//previously the render function, render code was moved to a method below
		}
	
		@Override
		public ModelPart root() {
return this.root;
		}
	
		
	}