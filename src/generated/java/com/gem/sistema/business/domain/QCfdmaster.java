package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCfdmaster is a Querydsl query type for Cfdmaster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCfdmaster extends EntityPathBase<Cfdmaster> {

    private static final long serialVersionUID = 1133807439L;

    public static final QCfdmaster cfdmaster = new QCfdmaster("cfdmaster");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath cadorig = createString("cadorig");

    public final StringPath callenumemi = createString("callenumemi");

    public final StringPath callenumrec = createString("callenumrec");

    public final StringPath cancelacion = createString("cancelacion");

    public final StringPath capturo = createString("capturo");

    public final StringPath ciudademi = createString("ciudademi");

    public final StringPath ciudadrec = createString("ciudadrec");

    public final StringPath coloniaemi = createString("coloniaemi");

    public final StringPath coloniarec = createString("coloniarec");

    public final StringPath cpemi = createString("cpemi");

    public final StringPath cprec = createString("cprec");

    public final StringPath delegemi = createString("delegemi");

    public final StringPath delegrec = createString("delegrec");

    public final StringPath estadoemi = createString("estadoemi");

    public final StringPath estadorec = createString("estadorec");

    public final DateTimePath<java.util.Date> fecha = createDateTime("fecha", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechacancel = createDateTime("fechacancel", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechacap = createDateTime("fechacap", java.util.Date.class);

    public final NumberPath<Long> folfinal = createNumber("folfinal", Long.class);

    public final NumberPath<Long> folinicio = createNumber("folinicio", Long.class);

    public final NumberPath<Long> folio = createNumber("folio", Long.class);

    public final StringPath formapago = createString("formapago");

    public final StringPath hora = createString("hora");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idref = createNumber("idref", Long.class);

    public final NumberPath<Long> idsector = createNumber("idsector", Long.class);

    public final NumberPath<java.math.BigDecimal> iva = createNumber("iva", java.math.BigDecimal.class);

    public final StringPath nocertif = createString("nocertif");

    public final StringPath nomemi = createString("nomemi");

    public final StringPath nomrec = createString("nomrec");

    public final StringPath paisemi = createString("paisemi");

    public final StringPath paisrec = createString("paisrec");

    public final StringPath rfcemi = createString("rfcemi");

    public final StringPath rfcrec = createString("rfcrec");

    public final StringPath serie = createString("serie");

    public final NumberPath<java.math.BigDecimal> subtotal = createNumber("subtotal", java.math.BigDecimal.class);

    public final StringPath tipcomp = createString("tipcomp");

    public final NumberPath<java.math.BigDecimal> total = createNumber("total", java.math.BigDecimal.class);

    public final StringPath totletra = createString("totletra");

    public final StringPath userid = createString("userid");

    public QCfdmaster(String variable) {
        super(Cfdmaster.class, forVariable(variable));
    }

    public QCfdmaster(Path<? extends Cfdmaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCfdmaster(PathMetadata<?> metadata) {
        super(Cfdmaster.class, metadata);
    }

}

