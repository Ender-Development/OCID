package io.klebe.ocid

import net.minecraft.creativetab.CreativeTabs
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger
import org.cyclops.cyclopscore.init.ModBaseVersionable
import org.cyclops.cyclopscore.init.RecipeHandler
import org.cyclops.cyclopscore.proxy.ICommonProxy

@Mod(
        modid = OCIDMeta.MODID,
        name = OCIDMeta.NAME,
        version = OCIDMeta.VERSION,
        dependencies = OCIDMeta.DEPENDENCIES,
        modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter")
object OCID : ModBaseVersionable(OCIDMeta.MODID, OCIDMeta.NAME, OCIDMeta.VERSION) {

    @JvmStatic
    val meta = OCIDMeta;

    val MODID: String get() = meta.MODID
    val NAME: String get() = meta.NAME
    val VERSION: String get() = meta.VERSION
    val DEPENDENCIES: String get() = meta.DEPENDENCIES

    private lateinit var logger: Logger

    @SidedProxy(serverSide = "io.klebe.ocid.CommonProxy", clientSide = "io.klebe.ocid.ClientProxy")
    private lateinit var private_proxy: CommonProxy

    val log
        get() = logger

    val proxy
        get() = private_proxy

    override fun constructRecipeHandler(): RecipeHandler? = null

    @Mod.EventHandler
    override fun preInit(e: FMLPreInitializationEvent) {
        super.preInit(e)
        this.logger = e.modLog
        //MinecraftForge.EVENT_BUS.register(this.proxy)
        this.proxy.preInit(e)
    }

    @Mod.EventHandler
    override fun init(e: FMLInitializationEvent) {
        super.init(e)
        this.proxy.init(e)
    }

    @Mod.EventHandler
    override fun postInit(e: FMLPostInitializationEvent) {
        super.postInit(e)
        this.proxy.postInit(e)
    }

    override fun constructDefaultCreativeTab(): CreativeTabs = CreativeTabs.REDSTONE
    override fun getProxy(): ICommonProxy = proxy
}