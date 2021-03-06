package de.Keyle.MyPet.util.hooks;

import de.Keyle.MyPet.MyPetApi;
import de.Keyle.MyPet.api.entity.MyPet;
import de.Keyle.MyPet.api.player.DonateCheck;
import de.Keyle.MyPet.api.player.MyPetPlayer;
import de.Keyle.MyPet.api.util.hooks.PluginHookName;
import de.Keyle.MyPet.util.PluginHook;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@PluginHookName("PlaceholderAPI")
public class PlaceholderApiHook extends PluginHook {
    Map<String, PlaceHolder> placeHolders = new HashMap<>();

    @Override
    public boolean onEnable() {
        boolean loaded = registerParentPlaceHolder();
        if (loaded) {
            registerPlaceholder();
        }
        return loaded;
    }

    public void registerPlaceholder() {
        placeHolders.put("name", new PlaceHolder<MyPet>(MyPet.class) {
            @Override
            public String getValue(MyPet pet) {
                return pet.getPetName();
            }
        });

        placeHolders.put("level", new PlaceHolder<MyPet>(MyPet.class) {
            @Override
            public String getValue(MyPet pet) {
                return "" + pet.getExperience().getLevel();
            }
        });

        placeHolders.put("exp", new PlaceHolder<MyPet>(MyPet.class) {
            @Override
            public String getValue(MyPet pet) {
                return "" + pet.getExp();
            }
        });

        placeHolders.put("type", new PlaceHolder<MyPet>(MyPet.class) {
            @Override
            public String getValue(MyPet pet) {
                return pet.getPetType().name();
            }
        });

        placeHolders.put("status", new PlaceHolder<MyPet>(MyPet.class) {
            @Override
            public String getValue(MyPet pet) {
                return pet.getStatus().name();
            }
        });

        placeHolders.put("health", new PlaceHolder<MyPet>(MyPet.class) {
            @Override
            public String getValue(MyPet pet) {
                return "" + pet.getHealth();
            }
        });

        placeHolders.put("health_max", new PlaceHolder<MyPet>(MyPet.class) {
            @Override
            public String getValue(MyPet pet) {
                return "" + pet.getMaxHealth();
            }
        });

        placeHolders.put("saturation", new PlaceHolder<MyPet>(MyPet.class) {
            @Override
            public String getValue(MyPet pet) {
                return "" + pet.getSaturation();
            }
        });

        placeHolders.put("uuid", new PlaceHolder<MyPet>(MyPet.class) {
            @Override
            public String getValue(MyPet pet) {
                return pet.getUUID().toString();
            }
        });

        placeHolders.put("skilltree_display", new PlaceHolder<MyPet>(MyPet.class) {
            @Override
            public String getValue(MyPet pet) {
                return pet.getSkilltree() != null ? pet.getSkilltree().getDisplayName() : null;
            }
        });

        placeHolders.put("skilltree_name", new PlaceHolder<MyPet>(MyPet.class) {
            @Override
            public String getValue(MyPet pet) {
                return pet.getSkilltree() != null ? pet.getSkilltree().getName() : null;
            }
        });

        placeHolders.put("world_group", new PlaceHolder<MyPet>(MyPet.class) {
            @Override
            public String getValue(MyPet pet) {
                return pet.getWorldGroup();
            }
        });

        placeHolders.put("player_is_premium", new PlaceHolder<MyPetPlayer>(MyPetPlayer.class) {
            @Override
            public String getValue(MyPetPlayer player) {
                return player.getDonationRank() == DonateCheck.DonationRank.Premium ? "yes" : "no";
            }
        });

        placeHolders.put("player_is_donator", new PlaceHolder<MyPetPlayer>(MyPetPlayer.class) {
            @Override
            public String getValue(MyPetPlayer player) {
                return player.getDonationRank() == DonateCheck.DonationRank.Donator ? "yes" : "no";
            }
        });

        placeHolders.put("player_is_creator", new PlaceHolder<MyPetPlayer>(MyPetPlayer.class) {
            @Override
            public String getValue(MyPetPlayer player) {
                return player.getDonationRank() == DonateCheck.DonationRank.Creator ? "yes" : "no";
            }
        });

        placeHolders.put("player_is_developer", new PlaceHolder<MyPetPlayer>(MyPetPlayer.class) {
            @Override
            public String getValue(MyPetPlayer player) {
                return player.getDonationRank() == DonateCheck.DonationRank.Developer ? "yes" : "no";
            }
        });

        placeHolders.put("player_is_translator", new PlaceHolder<MyPetPlayer>(MyPetPlayer.class) {
            @Override
            public String getValue(MyPetPlayer player) {
                return player.getDonationRank() == DonateCheck.DonationRank.Translator ? "yes" : "no";
            }
        });

        placeHolders.put("player_is_none", new PlaceHolder<MyPetPlayer>(MyPetPlayer.class) {
            @Override
            public String getValue(MyPetPlayer player) {
                return player.getDonationRank() == DonateCheck.DonationRank.None ? "yes" : "no";
            }
        });

        placeHolders.put("player_particle_rank", new PlaceHolder<MyPetPlayer>(MyPetPlayer.class) {
            @Override
            public String getValue(MyPetPlayer player) {
                return player.getDonationRank().name();
            }
        });

        placeHolders.put("player_language", new PlaceHolder<MyPetPlayer>(MyPetPlayer.class) {
            @Override
            public String getValue(MyPetPlayer player) {
                return player.getLanguage();
            }
        });

        placeHolders.put("player_uuid_internal", new PlaceHolder<MyPetPlayer>(MyPetPlayer.class) {
            @Override
            public String getValue(MyPetPlayer player) {
                return player.getInternalUUID().toString();
            }
        });

        placeHolders.put("has_pet", new PlaceHolder<Player>(Player.class) {
            @Override
            public String getValue(Player player) {
                return MyPetApi.getPlayerManager().isMyPetPlayer(player) && MyPetApi.getMyPetManager().hasActiveMyPet(player) ? "yes" : "no";
            }
        });
    }


    public boolean registerParentPlaceHolder() {
        PlaceholderExpansion myPetExpansion = new PlaceholderExpansion() {
            @Override
            public boolean canRegister() {
                return true;
            }

            @Override
            public String getAuthor() {
                return MyPetApi.getPlugin().getDescription().getAuthors().toString();
            }

            @Override
            public String getIdentifier() {
                return "mypet";
            }

            @Override
            public String getPlugin() {
                return "MyPet";
            }

            @Override
            public String getVersion() {
                return "1.0.0";
            }

            /**
             * This is the method called when a placeholder with our identifier is found and needs a value
             * We specify the value identifier in this method
             */
            @Override
            @SuppressWarnings("unchecked")
            public String onPlaceholderRequest(Player p, String identifier) {
                if (placeHolders.containsKey(identifier)) {
                    PlaceHolder placeHolder = placeHolders.get(identifier);

                    if (placeHolder.getHolderClass() == Player.class) {
                        return placeHolder.getValue(p);
                    }
                    if (placeHolder.getHolderClass() == MyPetPlayer.class) {
                        if (MyPetApi.getPlayerManager().isMyPetPlayer(p)) {
                            return placeHolder.getValue(MyPetApi.getPlayerManager().getMyPetPlayer(p));
                        }
                    }
                    if (placeHolder.getHolderClass() == MyPet.class) {
                        if (MyPetApi.getPlayerManager().isMyPetPlayer(p)) {
                            MyPetPlayer petPlayer = MyPetApi.getPlayerManager().getMyPetPlayer(p);
                            if (petPlayer.hasMyPet()) {
                                return placeHolder.getValue(petPlayer.getMyPet());
                            }
                        }
                    }
                }

                return null;
            }
        };

        return myPetExpansion.register();
    }

    abstract class PlaceHolder<T> {
        Class<T> clazz;

        public PlaceHolder(Class<T> clazz) {
            this.clazz = clazz;
        }

        public abstract String getValue(T holder);

        public Class<T> getHolderClass() {
            return clazz;
        }
    }
}