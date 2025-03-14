package dev.enjarai.trickster.spell.trick.tree;

import dev.enjarai.trickster.spell.Fragment;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.SpellPart;
import dev.enjarai.trickster.spell.fragment.FragmentType;
import dev.enjarai.trickster.spell.fragment.ListFragment;
import dev.enjarai.trickster.spell.fragment.NumberFragment;
import dev.enjarai.trickster.spell.fragment.VoidFragment;
import dev.enjarai.trickster.spell.blunder.BlunderException;
import dev.enjarai.trickster.spell.type.Signature;
import oshi.util.tuples.Pair;

import java.util.*;

public class LocateGlyphTrick extends AbstractMetaTrick<LocateGlyphTrick> {
    public LocateGlyphTrick() {
        super(Pattern.of(6, 7, 8, 2, 1, 0, 4, 8, 5), Signature.of(FragmentType.SPELL_PART, ANY, LocateGlyphTrick::locate));
    }

    public Fragment locate(SpellContext ctx, SpellPart spell, Fragment glyph) throws BlunderException {
        var address = search(spell, glyph);


        if (address == null)
            return VoidFragment.INSTANCE;
        else
            return new ListFragment(address.stream().map(num -> (Fragment) new NumberFragment(num)).toList());

    }

    //todo: improve memory efficiency
    private List<Integer> search(SpellPart spell, Fragment target) {
        Queue<Pair<Integer[], SpellPart>> queue = new LinkedList<>();

        queue.add(new Pair<>(new Integer[]{}, spell));
        while (!queue.isEmpty()) {

            var temp = queue.poll();

            if (temp.getB().glyph.equals(target))
                return Arrays.asList(temp.getA());

            var subParts = temp.getB().subParts;
            for (int i = 0; i < subParts.size(); i++) {
                var newAddress = Arrays.copyOfRange(temp.getA(), 0, temp.getA().length + 1);
                newAddress[temp.getA().length] = i;

                queue.add(new Pair<>(newAddress, subParts.get(i)));
            }
        }
        return null;
    }
}
