package com.tatvasoft.CommonUtility;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;

public class StringSequenceGenerator implements IdentifierGenerator, Configurable{

	 private String sequenceCallSyntax;
	    private String prefix;
	    @Override
	    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
	        final JdbcEnvironment jdbcEnvironment = serviceRegistry.getService(JdbcEnvironment.class);
	        final Dialect dialect = jdbcEnvironment.getDialect();

	        String mySequenceString = ConfigurationHelper.getString("sequence_name", params, "");
	        sequenceCallSyntax = dialect.getSequenceNextValString(mySequenceString);
	        prefix = ConfigurationHelper.getString("sequence_prefix", params, "");
	    }
	    @Override
	    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
	        long seqValue = ((Number) Session.class.cast(session)
	            .createSQLQuery(sequenceCallSyntax)
	            .uniqueResult()).longValue();
	        return prefix+seqValue;
	    }
	    
}
