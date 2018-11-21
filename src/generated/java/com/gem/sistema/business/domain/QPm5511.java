package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm5511 is a Querydsl query type for Pm5511
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm5511 extends EntityPathBase<Pm5511> {

    private static final long serialVersionUID = -1909741967L;

    public static final QPm5511 pm5511 = new QPm5511("pm5511");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> adu = createNumber("adu", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> adv = createNumber("adv", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> aeb = createNumber("aeb", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> aep = createNumber("aep", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> aes = createNumber("aes", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> amer = createNumber("amer", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> aocr = createNumber("aocr", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> apis = createNumber("apis", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cap = createNumber("cap", java.math.BigDecimal.class);

    public final StringPath capturo = createString("capturo");

    public final NumberPath<java.math.BigDecimal> daj = createNumber("daj", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ean = createNumber("ean", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> el = createNumber("el", java.math.BigDecimal.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<java.math.BigDecimal> maoa = createNumber("maoa", java.math.BigDecimal.class);

    public final NumberPath<Integer> mensual = createNumber("mensual", Integer.class);

    public final NumberPath<java.math.BigDecimal> mmme = createNumber("mmme", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mnpob = createNumber("mnpob", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mnpop = createNumber("mnpop", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mnpos = createNumber("mnpos", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mpv = createNumber("mpv", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mt = createNumber("mt", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mtam = createNumber("mtam", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> obf = createNumber("obf", java.math.BigDecimal.class);

    public final StringPath obs = createString("obs");

    public final StringPath obsgral = createString("obsgral");

    public final NumberPath<java.math.BigDecimal> otros = createNumber("otros", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pac = createNumber("pac", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> padu = createNumber("padu", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> padv = createNumber("padv", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> paeb = createNumber("paeb", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> paep = createNumber("paep", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> paes = createNumber("paes", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pamer = createNumber("pamer", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> paocr = createNumber("paocr", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> papis = createNumber("papis", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pcap = createNumber("pcap", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pdaj = createNumber("pdaj", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pean = createNumber("pean", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pel = createNumber("pel", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pmnpob = createNumber("pmnpob", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pmnpop = createNumber("pmnpop", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pmnpos = createNumber("pmnpos", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pmpv = createNumber("pmpv", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pmt = createNumber("pmt", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pobf = createNumber("pobf", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> podc = createNumber("podc", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> podcs = createNumber("podcs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> potros = createNumber("potros", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ppac = createNumber("ppac", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ppea = createNumber("ppea", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ppodc = createNumber("ppodc", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ppodcs = createNumber("ppodcs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pppea = createNumber("pppea", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> psppc = createNumber("psppc", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> psubb = createNumber("psubb", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> psubinf = createNumber("psubinf", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> psubof = createNumber("psubof", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> psubot = createNumber("psubot", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> psubsp = createNumber("psubsp", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> sppc = createNumber("sppc", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> subb = createNumber("subb", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> subinf = createNumber("subinf", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> subof = createNumber("subof", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> subot = createNumber("subot", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> subsp = createNumber("subsp", java.math.BigDecimal.class);

    public final StringPath userid = createString("userid");

    public QPm5511(String variable) {
        super(Pm5511.class, forVariable(variable));
    }

    public QPm5511(Path<? extends Pm5511> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm5511(PathMetadata<?> metadata) {
        super(Pm5511.class, metadata);
    }

}

