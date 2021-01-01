package com.codisimus.warper;

import java.util.Map;
import java.util.TreeMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

/**
 * A SerializableLocation represents a Bukkit Location object
 * This class is configuration serializable so that it may be
 * stored using Bukkit's configuration API
 */
@SerializableAs("WarperLocation")
public class SerializableLocation implements ConfigurationSerializable {
    private Location loc;

    public SerializableLocation(Location loc) {
        this.loc = loc;
    }

    /**
     * This constructor is used by Bukkit to create this object from a YAML configuration
     *
     * @param map The map which matches the return value of the serialize() method
     */
    public SerializableLocation(Map<String, Object> map) {
        //Check if the world for this location is loaded
        World world = Bukkit.getWorld((String) map.get("world"));
        if (world != null) {
            //Each coordinate we cast to double which was it's original type
            double x = (double) map.get("x");
            double y = (double) map.get("y");
            double z = (double) map.get("z");

            //Both yaw and pitch are loaded as type Double and then converted to float
            float yaw = ((Double) map.get("yaw")).floatValue();
            float pitch = ((Double) map.get("pitch")).floatValue();

            loc = new Location(world, x, y, z, yaw, pitch);
        } else {
            Warper.plugin.getLogger().severe("Invalid location, most likely due to missing world");
        }
    }

    /**
     * Returns a map representation of this object for use of serialization
     *
     * @return This location as a map of Strings to Objects
     */
    @Override
    public Map<String, Object> serialize() {
        Map map = new TreeMap();
        map.put("world", loc.getWorld().getName());
        map.put("x", loc.getX());
        map.put("y", loc.getY());
        map.put("z", loc.getZ());
        map.put("yaw", loc.getYaw());
        map.put("pitch", loc.getPitch());
        return map;
    }

    /**
     * Returns the Location object in its full form
     *
     * @return The location of this object
     */
    public Location getLocation() {
        return loc;
    }
}
