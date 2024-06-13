package tobinio.usefulsavedhotbars;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsefulSavedHotbars implements ModInitializer {

    public static String modID = "useful_saved_hotbars";

    public static final Logger LOGGER = LoggerFactory.getLogger("Useful Saved Hotbars");

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {

    }

    public static Identifier id(String path) {
        return Identifier.of(modID, path);
    }

}
