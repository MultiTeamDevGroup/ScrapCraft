package multiteam.scrapcraft.main.block;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.Registration;
import multiteam.scrapcraft.main.block.botcapsules.BotCapsuleBlock;
import multiteam.scrapcraft.main.block.botcapsules.BotCapsuleTileEntity;
import multiteam.scrapcraft.main.block.canisters.CanisterBlock;
import multiteam.scrapcraft.main.block.canisters.CanisterTileEntity;
import multiteam.scrapcraft.main.block.connectable.seat.SeatBlock;
import multiteam.scrapcraft.main.block.connectable.seat.SeatTileEntity;
import multiteam.scrapcraft.main.block.connectable.switcc.SwitchBlock;
import multiteam.scrapcraft.main.block.connectable.switcc.SwitchTileEntity;
import multiteam.scrapcraft.main.block.cookbot.CookBotBlock;
import multiteam.scrapcraft.main.block.cookbot.CookBotTileEntity;
import multiteam.scrapcraft.main.block.observerbot.ObserverBotBlock;
import multiteam.scrapcraft.main.block.observerbot.ObserverBotTileEntity;
import multiteam.scrapcraft.main.block.signs.SignBlock;
import multiteam.scrapcraft.main.block.tiles.multidir.MultiDirectionalTileBlock;
import multiteam.scrapcraft.main.block.tiles.multidir.MultiDirectionalTileBlockItem;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("ConstantConditions")
public class ModBlocks {

    //ConnectablePart
    public static final RegistryObject<Block> SEAT_BLOCK = registerWithItem("seat_block", () -> new SeatBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).strength(0, 0).harvestLevel(0).sound(SoundType.ANVIL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_PARTS));
    public static final RegistryObject<TileEntityType<SeatTileEntity>> SEAT_TILE = Registration.TILE_ENTITY_TYPES.register("seat_tile", () -> TileEntityType.Builder.of(SeatTileEntity::new, SEAT_BLOCK.get()).build(null));
    public static final RegistryObject<Block> SWITCH_BLOCK = registerWithItem("switch_block", () -> new SwitchBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).strength(0, 0).harvestLevel(0).sound(SoundType.ANVIL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_PARTS));
    public static final RegistryObject<TileEntityType<SwitchTileEntity>> SWITCH_TILE = Registration.TILE_ENTITY_TYPES.register("switch_tile", () -> TileEntityType.Builder.of(SwitchTileEntity::new, SWITCH_BLOCK.get()).build(null));

    //Machines
    public static final RegistryObject<Block> COOKBOT_BLOCK = registerWithItem("cookbot_block", () -> new CookBotBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).strength(0, 0).harvestLevel(0).sound(SoundType.ANVIL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_MACHINES));
    public static final RegistryObject<TileEntityType<CookBotTileEntity>> COOKBOT_TILE = Registration.TILE_ENTITY_TYPES.register("cookbot_tile", () -> TileEntityType.Builder.of(CookBotTileEntity::new, COOKBOT_BLOCK.get()).build(null));
    public static final RegistryObject<Block> OBSERVERBOT_BLOCK = registerWithItem("observerbot_block", () -> new ObserverBotBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).strength(0, 0).harvestLevel(0).sound(SoundType.ANVIL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_MACHINES));
    public static final RegistryObject<TileEntityType<ObserverBotTileEntity>> OBSERVERBOT_TILE = Registration.TILE_ENTITY_TYPES.register("observerbot_tile", () -> TileEntityType.Builder.of(ObserverBotTileEntity::new, OBSERVERBOT_BLOCK.get()).build(null));

    //Blocks
    public static final RegistryObject<Block> METAL_1 = registerWithItem("metal_1", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> METAL_2 = registerWithItem("metal_2", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> METAL_3 = registerWithItem("metal_3", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> CONCRETE_1 = registerWithItem("concrete_1", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.STONE)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> CONCRETE_2 = registerWithItem("concrete_2", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.STONE)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> CONCRETE_3 = registerWithItem("concrete_3", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.STONE)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> PUNCHED_STEEL = registerWithItem("punched_steel", () -> new RenderSideBlock(AbstractBlock.Properties.of(Material.METAL).isViewBlocking(ModBlocks::never).dynamicShape().strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> SQUARE_MESH = registerWithItem("square_mesh", () -> new RenderSideBlock(AbstractBlock.Properties.of(Material.METAL).isViewBlocking(ModBlocks::never).dynamicShape().strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> INSULATION = registerWithItem("insulation", () -> new Block(AbstractBlock.Properties.of(Material.WOOL).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.WOOL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> CANISTER_BLOCK_SMALL = registerWithItem("canister_block_small", () -> new CanisterBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_RED).strength(0, 0).harvestLevel(0).sound(SoundType.ANVIL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<TileEntityType<CanisterTileEntity>> CANISTER_TILE_SMALL = Registration.TILE_ENTITY_TYPES.register("canister_tile_small", () -> TileEntityType.Builder.of(CanisterTileEntity::new, CANISTER_BLOCK_SMALL.get()).build(null));
    public static final RegistryObject<Block> ARROW_SIGN = registerWithItem("arrow_sign", () -> new SignBlock(AbstractBlock.Properties.of(Material.METAL).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> ARROW_LIGHT_BLOCK = registerWithItem("arrow_light_block", () -> new RedstoneLampBlock(AbstractBlock.Properties.of(Material.METAL).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> BARRIER_BLOCK = registerWithItem("barrier_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(4.0f, 4.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> PATH_LIGHT = registerWithItem("path_light", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(3.0f, 3.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> CONCRETE_SLAB = registerWithItem("concrete_slab", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.STONE)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> RUSTED_METAL = registerWithItem("rusted_metal", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(3.0f, 3.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> NET_BLOCK = registerWithItem("net_block", () -> new RenderSideBlock(AbstractBlock.Properties.of(Material.METAL).isViewBlocking(ModBlocks::never).dynamicShape().strength(3.0f, 3.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> THICK_NET_BLOCK = registerWithItem("thick_net_block", () -> new RenderSideBlock(AbstractBlock.Properties.of(Material.METAL).isViewBlocking(ModBlocks::never).dynamicShape().strength(3.0f, 3.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> STRIPED_NET_BLOCK = registerWithItem("striped_net_block", () -> new RenderSideBlock(AbstractBlock.Properties.of(Material.METAL).isViewBlocking(ModBlocks::never).dynamicShape().strength(3.0f, 3.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> WORN_METAL = registerWithItem("worn_metal", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(3.0f, 3.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> CHALLANGE_BLOCK_1 = registerWithItem("challenge_block_1", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.STONE)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> CHALLANGE_BLOCK_2 = registerWithItem("challenge_block_2", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.STONE)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> ALUMINIUM = registerWithItem("aluminium", () -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.METAL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));
    public static final RegistryObject<Block> TILES_BLOCK = registerWithItem("tiles_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(4.0f, 5.0f).harvestLevel(1).sound(SoundType.STONE)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));

    //Capsules
    public static final RegistryObject<Block> TAPEBOT_CAPSULE_BLOCK = registerWithItem("tapebot_capsule_block", () -> new BotCapsuleBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLUE).strength(0, 0).harvestLevel(0).sound(SoundType.GLASS)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_MACHINES));
    public static final RegistryObject<TileEntityType<BotCapsuleTileEntity>> TAPEBOT_CAPSULE_TILE = Registration.TILE_ENTITY_TYPES.register("tapebot_capsule_tile", () -> TileEntityType.Builder.of(BotCapsuleTileEntity::new, TAPEBOT_CAPSULE_BLOCK.get()).build(null));

    public static final RegistryObject<Block> TESTBLOCK21 = registerWithItemCustom("testblock", () -> new MultiDirectionalTileBlock(AbstractBlock.Properties.of(Material.STONE)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_BLOCKS));


    public static void register() {
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerWithItem(String name, Supplier<T> block, Item.Properties itemProperties) {
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), itemProperties));
        return ret;
    }

    private static <T extends Block> RegistryObject<T> registerWithItemCustom(String name, Supplier<T> block, Item.Properties itemProperties) {
        RegistryObject<T> ret = Registration.BLOCKS.register(name, block);
        Registration.ITEMS.register(name, () -> new MultiDirectionalTileBlockItem(ret.get(), itemProperties));
        return ret;
    }

    private static boolean never(BlockState p_235436_0_, IBlockReader p_235436_1_, BlockPos p_235436_2_) {
        return false;
    }

}
