/*
 * Copyright (c) 2016, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *    This product includes software developed by Adam <Adam@sigterm.info>
 * 4. Neither the name of the Adam <Adam@sigterm.info> nor the
 *    names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY Adam <Adam@sigterm.info> ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Adam <Adam@sigterm.info> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.runelite.api;

import java.util.Arrays;

public class Client
{
	private net.runelite.rs.api.Client client;

	public Client(net.runelite.rs.api.Client client)
	{
		this.client = client;
	}

	public Player getLocalPlayer()
	{
		if (client.getLocalPlayer() == null)
			return null;
		
		return new Player(this, client.getLocalPlayer());
	}

	public NPC[] getNpcs()
	{
		return Arrays.stream(client.getCachedNPCs())
			.map(npc -> npc != null ? new NPC(this, npc) : null)
			.toArray(size -> new NPC[size]);
	}

	public Player[] getPlayers()
	{
		return Arrays.stream(client.getCachedPlayers())
			.map(player -> player != null ? new Player(this, player) : null)
			.toArray(size -> new Player[size]);
	}

	public int[] getBoostedSkillLevels()
	{
		return client.getBoostedSkillLevels();
	}

	public int[] getRealSkillLevels()
	{
		return client.getRealSkillLevels();
	}

	public int[] getSkillExperiences()
	{
		return client.getSkillExperiences();
	}

	public void sendGameMessage(String message)
	{
		client.sendGameMessage(99, "", message);
	}

	public GameState getGameState()
	{
		return GameState.of(client.getGameState());
	}
}