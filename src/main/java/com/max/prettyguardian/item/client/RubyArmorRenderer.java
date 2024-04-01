package com.max.prettyguardian.item.client;

import com.max.prettyguardian.item.custom.RubyArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class RubyArmorRenderer extends GeoArmorRenderer<RubyArmorItem> {
    public RubyArmorRenderer() {
        super(new RubyArmorModel());
    }
}
