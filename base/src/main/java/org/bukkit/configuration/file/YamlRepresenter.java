package org.bukkit.configuration.file;


import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.representer.SafeRepresenter.RepresentMap;
import org.bukkit.configuration.ConfigurationSection;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.representer.Representer;

public class YamlRepresenter extends Representer {

    public YamlRepresenter()
    {
        this.multiRepresenters.put(ConfigurationSection.class, new RepresentConfigurationSection(null));
    }

    private class RepresentConfigurationSection
            extends SafeRepresenter.RepresentMap
    {
        private RepresentConfigurationSection()
        {
            super();
        }

        public Node representData(Object data)
        {
            return super.representData(((ConfigurationSection)data).getValues(false));
        }
    }
}
