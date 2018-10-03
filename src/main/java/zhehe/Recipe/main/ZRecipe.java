package zhehe.Recipe.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.recipe.crafting.Ingredient;
import org.spongepowered.api.item.recipe.crafting.ShapedCraftingRecipe;
import org.spongepowered.api.item.recipe.crafting.ShapelessCraftingRecipe;
import org.spongepowered.api.item.recipe.smelting.SmeltingRecipe;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.DyeColors;
import org.spongepowered.api.data.type.GoldenApples;
import org.spongepowered.api.data.type.TreeTypes;

import com.google.inject.Inject;

import zhehe.Recipe.config.Configure;

@Plugin(id = ZRecipe.PLUGIN_ID, name = ZRecipe.PLUGIN_NAME, version = ZRecipe.PLUGIN_VERSION, authors = "zhehe")
public class ZRecipe {
	public static final String PLUGIN_ID = "zrecipe";
	public static final String PLUGIN_NAME = "ZRecipe";
	public static final String PLUGIN_VERSION = "1.0";
	
	@Inject
	@ConfigDir(sharedRoot = false)
	private Path configDir;

	@Inject
	private Logger logger;
	
	@Inject
	private static ZRecipe instance;

    public void SendTerminalMessage(String str) {
    	logger.info(str);
    }

    public Path getconfigDir() {
    	return configDir;
    }
    
	@Listener
	public void onReload(GameReloadEvent e) {
		logger.info("[ZRecipe] Please restart server...");
	}

    
	@Listener
	public void onGamePreInitializationEvent(GamePreInitializationEvent e) {
		instance = this;
		Configure.getConfig().init(instance);
		
		logger.info("zhehe's Title good to go!");
		// Create Configuration Directory for CustomPlayerCount
		if (!Files.exists(configDir)) {
			try {
				Files.createDirectories(configDir);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		Configure.getConfig().init_config();
		logger.info("[ZRecipe] Config has been loaded.");
		
		logger.info("[ZRecipe] Begin to register recipes.");
		RegisterRecipe();
		logger.info("[ZRecipe] Sucessfully register recipes.");
	}
	
	private void RegisterRecipe() {
		Configure config = Configure.getConfig();
		if(config.get_WoolToString()) {
			Sponge.getRegistry().getCraftingRecipeRegistry().register(WooltoStringRecipe());
		}
		if(config.get_BeetrootToSugar()) {
			Sponge.getRegistry().getCraftingRecipeRegistry().register(BeetrootToSugarRecipe());
		}
		if(config.get_LeatherToRabbitHide()) {
			Sponge.getRegistry().getCraftingRecipeRegistry().register(LeatherToRabbitHideRecipe());
		}
		if(config.get_TotemOfUndying()) {
			Sponge.getRegistry().getCraftingRecipeRegistry().register(TotemOfUndyingRecipe());
		}
		if(config.get_StringToCobweb()) {
			Sponge.getRegistry().getCraftingRecipeRegistry().register(StringToCobWebRecipe());
		}
		if(config.get_DeadBush()) {
			Sponge.getRegistry().getCraftingRecipeRegistry().register(DeadBushRecipe());
		}
		if(config.get_IronHouseArmor()) {
			Sponge.getRegistry().getCraftingRecipeRegistry().register(IronHouseArmorRecipe());
		}
		if(config.get_GoldenHouseArmor()) {
			Sponge.getRegistry().getCraftingRecipeRegistry().register(GoldenHouseArmorRecipe());
		}
		if(config.get_DiamondHouseArmor()) {
			Sponge.getRegistry().getCraftingRecipeRegistry().register(DiamondHouseArmorRecipe());
		}
		if(config.get_ShulkerShell()) {
			Sponge.getRegistry().getCraftingRecipeRegistry().register(ShulkerShellRecipe());
		}
		
		if(config.get_RottenfleshToLeather()) {
			Sponge.getRegistry().getSmeltingRecipeRegistry().register(RottenFleshToLeatherSmelt());
		}
		
		if(config.get_BucketToIronnugget()) {
			Sponge.getRegistry().getSmeltingRecipeRegistry().register(BucketToIronNuggetSmelt());
		}
		
		if(config.get_ShearsToIronnugget()) {
			Sponge.getRegistry().getSmeltingRecipeRegistry().register(ShearsToIronNuggetSmelt());
		}
	}
	
    private ShapelessCraftingRecipe WooltoStringRecipe() {
        return ShapelessCraftingRecipe.builder()
                .addIngredient(Ingredient.of(ItemTypes.WOOL))
                .result(ItemStack.of(ItemTypes.STRING, 4)).build("string_from_wool_recipe", this);
    }
    
    private ShapedCraftingRecipe BeetrootToSugarRecipe() {
        return ShapedCraftingRecipe.builder().aisle("ss", "ss")
                .where('s', Ingredient.of(ItemTypes.BEETROOT))
                .result(ItemStack.of(ItemTypes.SUGAR, 8)).build("sugar_from_beetroot", this);
    }
    
    private ShapelessCraftingRecipe LeatherToRabbitHideRecipe() {
    	ItemStack leather = ItemStack.of(ItemTypes.LEATHER, 1);
    	ItemStack boneMeal = ItemStack.of(ItemTypes.DYE, 1);
    	boneMeal.offer(Keys.DYE_COLOR, DyeColors.WHITE);

        return ShapelessCraftingRecipe.builder()
                .addIngredient(Ingredient.of(leather))
                .addIngredient(Ingredient.of(boneMeal))
                .result(ItemStack.of(ItemTypes.RABBIT_HIDE, 1)).build("rabbit_hide_from_leather_and_bone_recipe", this);
    }
    
    private ShapedCraftingRecipe TotemOfUndyingRecipe() {
    	ItemStack sapling1 = ItemStack.of(ItemTypes.SAPLING, 1);
    	sapling1.offer(Keys.TREE_TYPE, TreeTypes.OAK);
    	ItemStack sapling2 = ItemStack.of(ItemTypes.SAPLING, 1);
    	sapling2.offer(Keys.TREE_TYPE, TreeTypes.SPRUCE);
    	ItemStack sapling3 = ItemStack.of(ItemTypes.SAPLING, 1);
    	sapling3.offer(Keys.TREE_TYPE, TreeTypes.BIRCH);
    	ItemStack sapling4 = ItemStack.of(ItemTypes.SAPLING, 1);
    	sapling4.offer(Keys.TREE_TYPE, TreeTypes.JUNGLE);
    	
    	ItemStack emerald = ItemStack.of(ItemTypes.EMERALD_BLOCK, 1);
    	
    	ItemStack golden_apple = ItemStack.of(ItemTypes.GOLDEN_APPLE, 1);
    	golden_apple.offer(Keys.GOLDEN_APPLE_TYPE, GoldenApples.GOLDEN_APPLE);
    	
    	ItemStack speckled_melon = ItemStack.of(ItemTypes.SPECKLED_MELON, 1);
    	
    	ItemStack golden_carrot = ItemStack.of(ItemTypes.GOLDEN_CARROT, 1);
    	
        return ShapedCraftingRecipe.builder().aisle("aeb", "fgh", "ced")
                .where('a', Ingredient.of(sapling1))
                .where('b', Ingredient.of(sapling2))
                .where('c', Ingredient.of(sapling3))
                .where('d', Ingredient.of(sapling4))
                .where('e', Ingredient.of(emerald))
                .where('f', Ingredient.of(speckled_melon))
                .where('g', Ingredient.of(golden_apple))
                .where('h', Ingredient.of(golden_carrot))
                .result(ItemStack.of(ItemTypes.TOTEM_OF_UNDYING, 1)).build("TotemOfUndying_from_xxx", this);
    }
    
    private ShapedCraftingRecipe StringToCobWebRecipe() {
    	ItemStack string = ItemStack.of(ItemTypes.STRING, 1);
        return ShapedCraftingRecipe.builder().aisle("aaa", "a a", "aaa")
                .where('a', Ingredient.of(string))
                .result(ItemStack.of(ItemTypes.WEB, 1)).build("web_from_string", this);
    }
    
    private ShapelessCraftingRecipe DeadBushRecipe() {
    	ItemStack sapling = ItemStack.of(ItemTypes.SAPLING, 1);
    	sapling.offer(Keys.TREE_TYPE, TreeTypes.OAK);
    	ItemStack planks = ItemStack.of(ItemTypes.PLANKS, 1);
    	planks.offer(Keys.TREE_TYPE, TreeTypes.OAK);
    	ItemStack stick = ItemStack.of(ItemTypes.STICK, 1);
    	
        return ShapelessCraftingRecipe.builder()
                .addIngredient(Ingredient.of(sapling))
                .addIngredient(Ingredient.of(planks))
                .addIngredient(Ingredient.of(stick))
                .result(ItemStack.of(ItemTypes.DEADBUSH, 1)).build("deadbush_from_xxx", this);
    }
    
    private ShapedCraftingRecipe IronHouseArmorRecipe() {
    	ItemStack iron_ingot = ItemStack.of(ItemTypes.IRON_INGOT, 1);
    	ItemStack saddle = ItemStack.of(ItemTypes.SADDLE, 1);
    	
        return ShapedCraftingRecipe.builder().aisle("a a", "aba", "a a")
                .where('a', Ingredient.of(iron_ingot))
                .where('b', Ingredient.of(saddle))
                .result(ItemStack.of(ItemTypes.IRON_HORSE_ARMOR, 1)).build("IronHouseArmor_from_xxx", this);
    }
    
    private ShapedCraftingRecipe GoldenHouseArmorRecipe() {
    	ItemStack gold_ingot = ItemStack.of(ItemTypes.GOLD_INGOT, 1);
    	ItemStack saddle = ItemStack.of(ItemTypes.SADDLE, 1);
    	
        return ShapedCraftingRecipe.builder().aisle("a a", "aba", "a a")
                .where('a', Ingredient.of(gold_ingot))
                .where('b', Ingredient.of(saddle))
                .result(ItemStack.of(ItemTypes.GOLDEN_HORSE_ARMOR, 1)).build("GoldenHouseArmor_from_xxx", this);
    }
    
    private ShapedCraftingRecipe DiamondHouseArmorRecipe() {
    	ItemStack diamond = ItemStack.of(ItemTypes.DIAMOND, 1);
    	ItemStack saddle = ItemStack.of(ItemTypes.SADDLE, 1);
    	
        return ShapedCraftingRecipe.builder().aisle("a a", "aba", "a a")
                .where('a', Ingredient.of(diamond))
                .where('b', Ingredient.of(saddle))
                .result(ItemStack.of(ItemTypes.DIAMOND_HORSE_ARMOR, 1)).build("DiamondHouseArmor_from_xxx", this);
    }
    
    private ShapelessCraftingRecipe ShulkerShellRecipe() {
    	ItemStack rotten_flesh = ItemStack.of(ItemTypes.ROTTEN_FLESH, 1);
    	ItemStack bone = ItemStack.of(ItemTypes.BONE, 1);
    	ItemStack gunpowder = ItemStack.of(ItemTypes.GUNPOWDER, 1);
    	ItemStack string = ItemStack.of(ItemTypes.STRING, 1);
    	ItemStack spider_eye = ItemStack.of(ItemTypes.SPIDER_EYE, 1);
    	ItemStack ender_pearl = ItemStack.of(ItemTypes.ENDER_PEARL, 1);
    	ItemStack blaze_rod = ItemStack.of(ItemTypes.BLAZE_ROD, 1);
    	ItemStack ghast_tear = ItemStack.of(ItemTypes.GHAST_TEAR, 1);
    	
        return ShapelessCraftingRecipe.builder()
                .addIngredient(Ingredient.of(rotten_flesh))
                .addIngredient(Ingredient.of(bone))
                .addIngredient(Ingredient.of(gunpowder))
                .addIngredient(Ingredient.of(string))
                .addIngredient(Ingredient.of(spider_eye))
                .addIngredient(Ingredient.of(ender_pearl))
                .addIngredient(Ingredient.of(blaze_rod))
                .addIngredient(Ingredient.of(ghast_tear))
                .result(ItemStack.of(ItemTypes.SHULKER_SHELL, 4)).build("shulker_shell_from_xxx", this);
    }
    
    private SmeltingRecipe RottenFleshToLeatherSmelt() {
        return SmeltingRecipe.builder().ingredient(ItemTypes.ROTTEN_FLESH).result(ItemStack.of(ItemTypes.LEATHER, 1)).build();
    }
    
    private SmeltingRecipe BucketToIronNuggetSmelt() {
        return SmeltingRecipe.builder().ingredient(ItemTypes.BUCKET).result(ItemStack.of(ItemTypes.IRON_NUGGET, 1)).build();
    }
    
    private SmeltingRecipe ShearsToIronNuggetSmelt() {
        return SmeltingRecipe.builder().ingredient(ItemTypes.SHEARS).result(ItemStack.of(ItemTypes.IRON_NUGGET, 1)).build();
    }
}
