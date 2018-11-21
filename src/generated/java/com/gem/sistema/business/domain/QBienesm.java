package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBienesm is a Querydsl query type for Bienesm
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBienesm extends EntityPathBase<Bienesm> {

    private static final long serialVersionUID = 1318954139L;

    public static final QBienesm bienesm = new QBienesm("bienesm");

    public final StringPath area = createString("area");

    public final NumberPath<Integer> campo1 = createNumber("campo1", Integer.class);

    public final StringPath campo2 = createString("campo2");

    public final StringPath campo3 = createString("campo3");

    public final StringPath capturo = createString("capturo");

    public final NumberPath<Integer> conpol = createNumber("conpol", Integer.class);

    public final NumberPath<Integer> consec = createNumber("consec", Integer.class);

    public final NumberPath<java.math.BigDecimal> costo = createNumber("costo", java.math.BigDecimal.class);

    public final StringPath cuenta = createString("cuenta");

    public final NumberPath<java.math.BigDecimal> depreacum = createNumber("depreacum", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> depreperiodo = createNumber("depreperiodo", java.math.BigDecimal.class);

    public final StringPath estado = createString("estado");

    public final StringPath factura = createString("factura");

    public final DatePath<java.util.Date> fecalta = createDate("fecalta", java.util.Date.class);

    public final DatePath<java.util.Date> fecbaja = createDate("fecbaja", java.util.Date.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final DatePath<java.util.Date> fecfactura = createDate("fecfactura", java.util.Date.class);

    public final DatePath<java.util.Date> fecpol = createDate("fecpol", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath inventario = createString("inventario");

    public final StringPath localidad = createString("localidad");

    public final StringPath marca = createString("marca");

    public final NumberPath<Integer> mes = createNumber("mes", Integer.class);

    public final StringPath modelo = createString("modelo");

    public final StringPath motor = createString("motor");

    public final StringPath nomcta = createString("nomcta");

    public final StringPath nommueble = createString("nommueble");

    public final StringPath obs = createString("obs");

    public final StringPath proveedor = createString("proveedor");

    public final StringPath recurso = createString("recurso");

    public final StringPath resguardatario = createString("resguardatario");

    public final StringPath resguardo = createString("resguardo");

    public final StringPath scta = createString("scta");

    public final StringPath serie = createString("serie");

    public final StringPath sscta = createString("sscta");

    public final StringPath ssscta = createString("ssscta");

    public final StringPath sssscta = createString("sssscta");

    public final StringPath tippol = createString("tippol");

    public final StringPath tvidautil = createString("tvidautil");

    public final StringPath userid = createString("userid");

    public QBienesm(String variable) {
        super(Bienesm.class, forVariable(variable));
    }

    public QBienesm(Path<? extends Bienesm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBienesm(PathMetadata<?> metadata) {
        super(Bienesm.class, metadata);
    }

}

