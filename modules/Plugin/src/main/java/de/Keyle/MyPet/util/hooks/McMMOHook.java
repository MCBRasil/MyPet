/*
 * This file is part of MyPet
 *
 * Copyright © 2011-2016 Keyle
 * MyPet is licensed under the GNU Lesser General Public License.
 *
 * MyPet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MyPet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package de.Keyle.MyPet.util.hooks;

import com.gmail.nossr50.api.PartyAPI;
import de.Keyle.MyPet.api.Configuration;
import de.Keyle.MyPet.api.util.hooks.PluginHookName;
import de.Keyle.MyPet.api.util.hooks.types.PartyHook;
import de.Keyle.MyPet.api.util.hooks.types.PlayerVersusPlayerHook;
import de.Keyle.MyPet.util.PluginHook;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@PluginHookName("mcMMO")
public class McMMOHook extends PluginHook implements PlayerVersusPlayerHook, PartyHook {

    @Override
    public boolean onEnable() {
        return Configuration.Hooks.USE_McMMO;
    }

    @Override
    public boolean canHurt(Player attacker, Player defender) {
        try {
            return !PartyAPI.inSameParty(attacker, defender);
        } catch (Throwable ignored) {
        }
        return true;
    }

    @Override
    public boolean isInParty(Player player) {
        try {
            return PartyAPI.inParty(player);
        } catch (Exception ignored) {
        }
        return false;
    }

    @Override
    public List<Player> getPartyMembers(Player player) {
        try {
            if (PartyAPI.inParty(player)) {
                List<Player> members = new ArrayList<>();
                String partyName = PartyAPI.getPartyName(player);
                for (Player member : PartyAPI.getOnlineMembers(partyName)) {
                    members.add(member);
                }
                return members;
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}