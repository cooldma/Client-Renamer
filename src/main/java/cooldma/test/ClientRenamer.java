package cooldma.test;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


public class ClientRenamer implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("client-renamer");
	private static boolean enabled = true;
	static MinecraftClient mc = MinecraftClient.getInstance();
	private static String clientBrand = "Client-Renamer";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Client-Renamer");
		ClientCommandRegistrationCallback.EVENT.register(ClientRenamer::registerCommands);
		try {
//			clientBrand = mc.getVersionType();
			clientBrand = mc.player.getServerBrand();
		} catch (Exception e) {
			LOGGER.error("An error has occurred, please report this stacktrace: " + Arrays.toString(e.getStackTrace()));
		}
	}

	public static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
		ClientRenamerCommand.register(dispatcher);
	}

	public static boolean isEnabled() {
		return enabled;
	}

	public static String getClientBrand() {
		return clientBrand;
	}

	public static int toggleStatus() {
		enabled = !enabled;
		mc.player.sendMessage(Text.of("§3[Client-Renamer] §bStatus: " + (enabled ? "§aOn" : "§cOff")));
		return 0;
	}

	public static int changeClientBrand(String newClientBrand) {
		clientBrand = newClientBrand;
		mc.player.sendMessage(Text.of("§3[Client-Renamer] §bSet client brand to: " + "§6" + clientBrand));
		return 0;
	}
}