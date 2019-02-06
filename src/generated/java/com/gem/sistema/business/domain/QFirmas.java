package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFirmas is a Querydsl query type for Firmas
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFirmas extends EntityPathBase<Firmas> {

    private static final long serialVersionUID = 2097112356L;

    public static final QFirmas firmas = new QFirmas("firmas");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath campo1 = createString("campo1");

    public final StringPath campo10 = createString("campo10");

    public final StringPath campo11 = createString("campo11");

    public final StringPath campo12 = createString("campo12");

    public final StringPath campo13 = createString("campo13");

    public final StringPath campo14 = createString("campo14");

    public final StringPath campo15 = createString("campo15");

    public final StringPath campo2 = createString("campo2");

    public final NumberPath<Integer> campo3 = createNumber("campo3", Integer.class);

    public final StringPath campo4 = createString("campo4");

    public final StringPath campo5 = createString("campo5");

    public final StringPath campo6 = createString("campo6");

    public final StringPath campo7 = createString("campo7");

    public final StringPath campo8 = createString("campo8");

    public final StringPath campo9 = createString("campo9");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath l1 = createString("l1");

    public final StringPath l10 = createString("l10");

    public final StringPath l11 = createString("l11");

    public final StringPath l12 = createString("l12");

    public final StringPath l13 = createString("l13");

    public final StringPath l14 = createString("l14");

    public final StringPath l15 = createString("l15");

    public final StringPath l16 = createString("l16");

    public final StringPath l17 = createString("l17");

    public final StringPath l18 = createString("l18");

    public final StringPath l19 = createString("l19");

    public final StringPath l2 = createString("l2");

    public final StringPath l20 = createString("l20");

    public final StringPath l21 = createString("l21");

    public final StringPath l22 = createString("l22");

    public final StringPath l23 = createString("l23");

    public final StringPath l24 = createString("l24");

    public final StringPath l25 = createString("l25");

    public final StringPath l26 = createString("l26");

    public final StringPath l27 = createString("l27");

    public final StringPath l28 = createString("l28");

    public final StringPath l29 = createString("l29");

    public final StringPath l3 = createString("l3");

    public final StringPath l30 = createString("l30");

    public final StringPath l31 = createString("l31");

    public final StringPath l32 = createString("l32");

    public final StringPath l33 = createString("l33");

    public final StringPath l34 = createString("l34");

    public final StringPath l35 = createString("l35");

    public final StringPath l36 = createString("l36");

    public final StringPath l37 = createString("l37");

    public final StringPath l4 = createString("l4");

    public final StringPath l5 = createString("l5");

    public final StringPath l6 = createString("l6");

    public final StringPath l7 = createString("l7");

    public final StringPath l8 = createString("l8");

    public final StringPath l9 = createString("l9");

    public final StringPath n1 = createString("n1");

    public final StringPath n10 = createString("n10");

    public final StringPath n11 = createString("n11");

    public final StringPath n12 = createString("n12");

    public final StringPath n13 = createString("n13");

    public final StringPath n14 = createString("n14");

    public final StringPath n15 = createString("n15");

    public final StringPath n16 = createString("n16");

    public final StringPath n17 = createString("n17");

    public final StringPath n18 = createString("n18");

    public final StringPath n19 = createString("n19");

    public final StringPath n2 = createString("n2");

    public final StringPath n20 = createString("n20");

    public final StringPath n21 = createString("n21");

    public final StringPath n22 = createString("n22");

    public final StringPath n23 = createString("n23");

    public final StringPath n24 = createString("n24");

    public final StringPath n25 = createString("n25");

    public final StringPath n26 = createString("n26");

    public final StringPath n27 = createString("n27");

    public final StringPath n28 = createString("n28");

    public final StringPath n29 = createString("n29");

    public final StringPath n3 = createString("n3");

    public final StringPath n30 = createString("n30");

    public final StringPath n31 = createString("n31");

    public final StringPath n32 = createString("n32");

    public final StringPath n33 = createString("n33");

    public final StringPath n34 = createString("n34");

    public final StringPath n35 = createString("n35");

    public final StringPath n36 = createString("n36");

    public final StringPath n37 = createString("n37");

    public final StringPath n4 = createString("n4");

    public final StringPath n5 = createString("n5");

    public final StringPath n6 = createString("n6");

    public final StringPath n7 = createString("n7");

    public final StringPath n8 = createString("n8");

    public final StringPath n9 = createString("n9");

    public final StringPath userid = createString("userid");

    public QFirmas(String variable) {
        super(Firmas.class, forVariable(variable));
    }

    public QFirmas(Path<? extends Firmas> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFirmas(PathMetadata<?> metadata) {
        super(Firmas.class, metadata);
    }

}

