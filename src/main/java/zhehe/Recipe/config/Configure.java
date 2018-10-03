package zhehe.Recipe.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import zhehe.Recipe.main.ZRecipe;

public class Configure {
	private static Configure config = new Configure();
	
	private Configure() {
		
	}
	
	public static Configure getConfig() {
		return config;
	}
	
	private ZRecipe zrecipe;
	
	private Path configFile;
	
	ConfigurationLoader<CommentedConfigurationNode> loader;
	CommentedConfigurationNode rootNode;
	
	public void init(ZRecipe in) {
		zrecipe = in;
		Path configDir = zrecipe.getconfigDir();
		configFile = Paths.get(configDir + "/config.txt");
		loader = HoconConfigurationLoader.builder().setPath(configFile).build();
	}
	
	public void init_config() {
		zrecipe.SendTerminalMessage("[ZRecipe] Try to read the ZRecipe config file.");
		if (!Files.exists(configFile)) {
			try {
				zrecipe.SendTerminalMessage("[ZRecipe] Could not find a valid config file, so will create one.");
				Files.createFile(configFile);
				load_config();
				build();
				save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			load_config();
		}
	}
	
	private void load_config() {
		try {
			rootNode = loader.load();

			switch (rootNode.getNode("ConfigVersion").getInt()) {
				case 1: {
					// current version
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void build() {
		rootNode.getNode("ConfigVersion").setValue(1).setComment("Config file version. Do not change it!!");
		rootNode.getNode("recipe", "WoolToString").setValue(true);
		rootNode.getNode("recipe", "BeetrootToSugar").setValue(true);
		rootNode.getNode("recipe", "Bottleoenchanting").setValue(true);
		rootNode.getNode("recipe", "LeatherToRabbitHide").setValue(true);
		rootNode.getNode("recipe", "TotemOfUndying").setValue(true);
		rootNode.getNode("recipe", "StringToCobweb").setValue(true);
		rootNode.getNode("recipe", "DeadBush").setValue(true);
		rootNode.getNode("recipe", "IronHouseArmor").setValue(true);
		rootNode.getNode("recipe", "GoldenHouseArmor").setValue(true);
		rootNode.getNode("recipe", "DiamondHouseArmor").setValue(true);
		rootNode.getNode("recipe", "ShulkerShell").setValue(true);
		
		rootNode.getNode("smelt_recipt", "RottenfleshToLeather").setValue(true);
		rootNode.getNode("smelt_recipt", "BucketToIronnugget").setValue(true);
		rootNode.getNode("smelt_recipt", "ShearsToIronnugget").setValue(true);
	}
	
	private void save() {
		try {
			loader.save(rootNode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean get_WoolToString() {
		return rootNode.getNode("recipe", "WoolToString").getBoolean();
	}
	
	public boolean get_BeetrootToSugar() {
		return rootNode.getNode("recipe", "BeetrootToSugar").getBoolean();
	}
	
	public boolean get_Bottleoenchanting() {
		return rootNode.getNode("recipe", "Bottleoenchanting").getBoolean();
	}
	
	public boolean get_LeatherToRabbitHide() {
		return rootNode.getNode("recipe", "LeatherToRabbitHide").getBoolean();
	}
	
	public boolean get_TotemOfUndying() {
		return rootNode.getNode("recipe", "TotemOfUndying").getBoolean();
	}
	
	public boolean get_StringToCobweb() {
		return rootNode.getNode("recipe", "StringToCobweb").getBoolean();
	}
	
	public boolean get_DeadBush() {
		return rootNode.getNode("recipe", "DeadBush").getBoolean();
	}
	
	public boolean get_IronHouseArmor() {
		return rootNode.getNode("recipe", "IronHouseArmor").getBoolean();
	}
	
	public boolean get_GoldenHouseArmor() {
		return rootNode.getNode("recipe", "GoldenHouseArmor").getBoolean();
	}
	
	public boolean get_DiamondHouseArmor() {
		return rootNode.getNode("recipe", "DiamondHouseArmor").getBoolean();
	}
	
	public boolean get_ShulkerShell() {
		return rootNode.getNode("recipe", "ShulkerShell").getBoolean();
	}
	
	public boolean get_RottenfleshToLeather() {
		return rootNode.getNode("smelt_recipt", "RottenfleshToLeather").getBoolean();
	}
	
	public boolean get_BucketToIronnugget() {
		return rootNode.getNode("smelt_recipt", "BucketToIronnugget").getBoolean();
	}
	
	public boolean get_ShearsToIronnugget() {
		return rootNode.getNode("smelt_recipt", "ShearsToIronnugget").getBoolean();
	}
}
