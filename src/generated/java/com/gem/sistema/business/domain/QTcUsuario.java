package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTcUsuario is a Querydsl query type for TcUsuario
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcUsuario extends EntityPathBase<TcUsuario> {

    private static final long serialVersionUID = -699557109L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTcUsuario tcUsuario = new QTcUsuario("tcUsuario");

    public final BooleanPath accountNonExpired = createBoolean("accountNonExpired");

    public final BooleanPath accountnonlocked = createBoolean("accountnonlocked");

    public final StringPath contrasenia = createString("contrasenia");

    public final BooleanPath credentialsNonExpired = createBoolean("credentialsNonExpired");

    public final BooleanPath habilitado = createBoolean("habilitado");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idEntidadAdmini = createNumber("idEntidadAdmini", Long.class);

    public final NumberPath<Long> idLocalidad = createNumber("idLocalidad", Long.class);

    public final StringPath nombre = createString("nombre");

    public final QTcRole role;

    public final QTcTipoUsuario tipoUsuario;

    public final StringPath usuario = createString("usuario");

    public final StringPath usuarioCtl = createString("usuarioCtl");

    public QTcUsuario(String variable) {
        this(TcUsuario.class, forVariable(variable), INITS);
    }

    public QTcUsuario(Path<? extends TcUsuario> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTcUsuario(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTcUsuario(PathMetadata<?> metadata, PathInits inits) {
        this(TcUsuario.class, metadata, inits);
    }

    public QTcUsuario(Class<? extends TcUsuario> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.role = inits.isInitialized("role") ? new QTcRole(forProperty("role")) : null;
        this.tipoUsuario = inits.isInitialized("tipoUsuario") ? new QTcTipoUsuario(forProperty("tipoUsuario")) : null;
    }

}

