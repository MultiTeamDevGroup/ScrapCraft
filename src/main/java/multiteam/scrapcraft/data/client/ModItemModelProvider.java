package multiteam.scrapcraft.data.client;

import multiteam.scrapcraft.ScrapCraft;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

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

        //Items
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        //Regular Items
        builder(itemGenerated, "tomato");

    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/"+name);
    }

}