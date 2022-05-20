package multiteam.scrapcraft.data.client;

import mcp.MethodsReturnNonnullByDefault;
import multiteam.scrapcraft.ScrapCraft;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ScrapCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Blockitems
        withExistingParent("metal_1", modLoc("block/metal_1"));
        withExistingParent("metal_2", modLoc("block/metal_2"));
        withExistingParent("metal_3", modLoc("block/metal_3"));
        withExistingParent("concrete_1", modLoc("block/concrete_1"));
        withExistingParent("concrete_2", modLoc("block/concrete_2"));
        withExistingParent("concrete_3", modLoc("block/concrete_3"));
        withExistingParent("insulation", modLoc("block/insulation"));
        withExistingParent("punched_steel", modLoc("block/punched_steel"));
        withExistingParent("square_mesh", modLoc("block/square_mesh"));
        withExistingParent("barrier_block", modLoc("block/barrier_block"));
        withExistingParent("path_light", modLoc("block/path_light"));
        withExistingParent("concrete_slab", modLoc("block/concrete_slab"));
        withExistingParent("rusted_metal", modLoc("block/rusted_metal"));
        withExistingParent("net_block", modLoc("block/net_block"));
        withExistingParent("thick_net_block", modLoc("block/thick_net_block"));
        withExistingParent("striped_net_block", modLoc("block/striped_net_block"));
        withExistingParent("worn_metal", modLoc("block/worn_metal"));
        withExistingParent("challenge_block_1", modLoc("block/challenge_block_1"));
        withExistingParent("challenge_block_2", modLoc("block/challenge_block_2"));
        withExistingParent("aluminium", modLoc("block/aluminium"));
        withExistingParent("arrow_light_block", modLoc("block/arrow_light_block"));
        withExistingParent("tiles_block", modLoc("block/tiles_block"));

        //Items
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        //Regular Items
        builder(itemGenerated, "tomato");
        builder(itemGenerated, "banana");
        builder(itemGenerated, "blueberry");
        builder(itemGenerated, "broccoli");
        builder(itemGenerated, "orange");
        builder(itemGenerated, "pineapple");

    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }

}