package com.github.telvarost.dispensertweaks.events.init;

import com.github.telvarost.dispensertweaks.ModHelper;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.registry.AfterBlockAndItemRegisterEvent;

public class RegistryListener {

    @EventListener
    public void handleAfterBlockAndItemRegisterEvent(AfterBlockAndItemRegisterEvent event)
    {
        ModHelper.ModHelperFields.blocksAndItemsRegistered = true;
        ModHelper.AttemptToSetStackSizeOfFluids();
    }
}
