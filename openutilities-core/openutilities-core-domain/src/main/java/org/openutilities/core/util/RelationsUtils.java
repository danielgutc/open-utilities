package org.openutilities.core.util;

import org.openutilities.core.domain.Relation;
import org.openutilities.core.domain.Resource;
import org.openutilities.core.exceptions.DomainRuleException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Relations utils encapsulation
 */
public class RelationsUtils
{

    /**
     * Verify commeon relation rules: overlaps and multiple open relations for the same resource specs
     * @param relations
     * @param owner
     */
    public static void verifyRelations(List<Relation> relations, Resource owner)
    {
        // TODO verify there are not overlaps in the relations
        relations.stream()
                .filter(cr -> cr.getToDt() == null)
                .map(cr -> cr.getToResource().getSpecId())
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet().stream()
                .forEach(c ->
                {
                    if (c.getValue() > 1) {
                        throw new DomainRuleException(owner, String.format("Multiple relations of spec %s open", c.getKey()));
                    }
                });
    }
}
