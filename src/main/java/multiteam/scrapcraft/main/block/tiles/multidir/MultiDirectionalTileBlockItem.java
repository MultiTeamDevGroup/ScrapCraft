package multiteam.scrapcraft.main.block.tiles.multidir;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MultiDirectionalTileBlockItem extends BlockItem {

    boolean buttonTestStatePrev;
    boolean buttonTestStateCurrent;

    public MultiDirectionalTileBlockItem(Block block, Properties prop) {
        super(block, prop);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int int1, boolean bool1) {
        if(!world.isClientSide){
            if(entity instanceof PlayerEntity){
                PlayerEntity player = ((PlayerEntity)entity);
                if(player.getMainHandItem() == stack){
                    //System.out.println("YYOS");
                    //Stacking if statements, eh? that sounds like fun i guess
                }
            }
        }
    }
}
