package optional.Utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import project4.UtilityClasses.Operation;

public class UtilityClasses {
	
	public static class Configuration implements Serializable
	{
		private static final long serialVersionUID = 1L;
		public Configuration(int sequenceNo, Integer[] shardsGroup,
				HashMap<Integer, List<Integer>> replicaGroupMap) {
			super();
			this.sequenceNo = sequenceNo;
			this.ShardsGroupId = shardsGroup;
			this.replicaGroupMap = replicaGroupMap;
		}
		public int sequenceNo;
		public Integer [] ShardsGroupId;
		public HashMap<Integer, List<Integer>> replicaGroupMap;
	}
	
	
	public static class LeaveArgs implements Serializable
	{
		private static final long serialVersionUID = 1L;
		int groupId;
		UUID uuid;
		public LeaveArgs(int groupId, UUID uuid) {
			super();
			this.groupId = groupId;
			this.uuid = uuid;
		}
		
		
	}
	
	public static class MoveArgs implements Serializable
	{
		private static final long serialVersionUID = 1L;
		public Integer shard;
		public Integer groupId;
		public UUID uuid;
		public MoveArgs(Integer shard, Integer groupId, UUID uuid) {
			super();
			this.shard = shard;
			this.groupId = groupId;
			this.uuid = uuid;
		}
	}
	public static class JoinArgs implements Serializable
	{
		private static final long serialVersionUID = 1L;
		public Integer groupId;
		public UUID uuid;
		public List<Integer> servers;
		public JoinArgs(Integer groupId, List<Integer> servers, UUID uuid) {
			super();
			this.groupId = groupId;
			this.servers = servers;
			this.uuid = uuid;
		}
		
	}
	public static class QueryArgs implements Serializable
	{
		private static final long serialVersionUID = 1L;
		public int configurationSequenceNumber;
		public UUID uuid;
		public QueryArgs(int configurationSequenceNumber, UUID uuid) {
			super();
			this.configurationSequenceNumber = configurationSequenceNumber;
			this.uuid = uuid;
		}
		
	}
	public static class QueryReply implements Serializable
	{
		private static final long serialVersionUID = 1L;
		Configuration configuration;
		public QueryReply(Configuration configuration) {
			super();
			this.configuration = configuration;
		}
	}

	 /** Write the object to a Base64 string. */
   private static String toString( Serializable o ) throws IOException {
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       ObjectOutputStream oos = new ObjectOutputStream( baos );
       oos.writeObject( o );
       oos.close();
       return Base64.getEncoder().encodeToString(baos.toByteArray()); 
   }


   /** Read the object from Base64 string. */
  private static Object fromString( String s ) throws IOException ,
                                                      ClassNotFoundException {
       byte [] data = Base64.getDecoder().decode( s );
       ObjectInputStream ois = new ObjectInputStream( 
                                       new ByteArrayInputStream(  data ) );
       Object o  = ois.readObject();
       ois.close();
       return o;
  }
  public static DbOperation decodeDbOperation(String s) throws ClassNotFoundException, IOException
  {
	   DbOperation operation = (DbOperation) fromString(s);
	   return operation;
  }
  public static String encodeDbOperation(DbOperation operation) throws IOException
  {
	   return toString(operation);
  }
  
  public static ShardOperation decodeShardOperation(String s) throws ClassNotFoundException, IOException
  {
	   ShardOperation operation = (ShardOperation) fromString(s);
	   return operation;
  }
  public static String encodeShardOperation(ShardOperation operation) throws IOException
  {
	   return toString(operation);
  }
  
  
	public static class PrepareArgs implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int sequenceNo;
		public PrepareArgs(int sequenceNo, int proposalNo, int key) {
			super();
			this.sequenceNo = sequenceNo;
			this.proposalNo = proposalNo;
			this.key = key;
		}
		int proposalNo;
		int key;
		public int getSequenceNo() {
			return sequenceNo;
		}
		public void setSequenceNo(int sequenceNo) {
			this.sequenceNo = sequenceNo;
		}
		public int getProposalNo() {
			return proposalNo;
		}
		public void setProposalNo(int proposalNo) {
			this.proposalNo = proposalNo;
		}
		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
	}
	
	public static class HostPorts implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String hostName;
		int port;
		public String getHostName() {
			return hostName;
		}
		public void setHostName(String hostName) {
			this.hostName = hostName;
		}
		public int getPort() {
			return port;
		}
		public HostPorts(String hostName, int port) {
			super();
			this.hostName = hostName;
			this.port = port;
		}
		public void setPort(int port) {
			this.port = port;
		}
	}
	public static class PrepareReply implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int highestPrepareNo;
		int highestProposalNo;
		boolean ok;
		public PrepareReply(int highestPrepareNo, int highestProposalNo,
				String value, boolean ok) {
			super();
			this.highestPrepareNo = highestPrepareNo;
			this.highestProposalNo = highestProposalNo;
			this.value = value;
			this.ok = ok;
		}
		
		public boolean isOk() {
			return ok;
		}

		public void setOk(boolean ok) {
			this.ok = ok;
		}

		public int getHighestPrepareNo() {
			return highestPrepareNo;
		}
		public void setHighestPrepareNo(int highestPrepareNo) {
			this.highestPrepareNo = highestPrepareNo;
		}
		public int getHighestProposalNo() {
			return highestProposalNo;
		}
		public void setHighestProposalNo(int highestProposalNo) {
			this.highestProposalNo = highestProposalNo;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		String value;
	}
	public static class AcceptArgs implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int sequenceNo;
		int proposalNo;
		String value;
		public int getSequenceNo() {
			return sequenceNo;
		}
		public void setSequenceNo(int sequenceNo) {
			this.sequenceNo = sequenceNo;
		}
		public int getProposalNo() {
			return proposalNo;
		}
		public void setProposalNo(int proposalNo) {
			this.proposalNo = proposalNo;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public AcceptArgs(int sequenceNo, int proposalNo, String value) {
			super();
			this.sequenceNo = sequenceNo;
			this.proposalNo = proposalNo;
			this.value = value;
		}
		
	}
	
	public static class AcceptReply implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		boolean OK;

		public boolean isOK() {
			return OK;
		}

		public void setOK(boolean oK) {
			OK = oK;
		}

		public AcceptReply(boolean oK) {
			super();
			OK = oK;
		}
		
	}
	
	public static class LearnArgs implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int sequenceNo;
		String value;
		int me;
		public LearnArgs(int sequenceNo, String value, int me, int maxDoneSeq) {
			super();
			this.sequenceNo = sequenceNo;
			this.value = value;
			this.me = me;
			MaxDoneSeq = maxDoneSeq;
		}
		public int getSequenceNo() {
			return sequenceNo;
		}
		public void setSequenceNo(int sequenceNo) {
			this.sequenceNo = sequenceNo;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public int getMe() {
			return me;
		}
		public void setMe(int me) {
			this.me = me;
		}
		public int getMaxDoneSeq() {
			return MaxDoneSeq;
		}
		public void setMaxDoneSeq(int maxDoneSeq) {
			MaxDoneSeq = maxDoneSeq;
		}
		int MaxDoneSeq;
	}
	
	public static class LearnReply implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		boolean OK;

		public LearnReply(boolean oK) {
			super();
			OK = oK;
		}

		public boolean isOK() {
			return OK;
		}

		public void setOK(boolean oK) {
			OK = oK;
		}
		
	}

	public static class AcceptorState implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int N_p;
		int N_a;
		String value;
		public int getN_p() {
			return N_p;
		}
		public AcceptorState(int n_p, int n_a, String value) {
			super();
			N_p = n_p;
			N_a = n_a;
			this.value = value;
		}
		public void setN_p(int n_p) {
			N_p = n_p;
		}
		public int getN_a() {
			return N_a;
		}
		public void setN_a(int n_a) {
			N_a = n_a;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}

	
	public static class Status implements Serializable
	{ 
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public String value;
		public boolean done;
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public boolean isDone() {
			return done;
		}
		public void setDone(boolean done) {
			this.done = done;
		}
		public Status(String value, boolean done) {
			super();
			this.value = value;
			this.done = done;
		}
		
	}
	
	public static class Response implements Serializable
	{ 
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public Object value;
		public boolean done;
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
		public boolean isDone() {
			return done;
		}
		public void setDone(boolean done) {
			this.done = done;
		}
		public Response(Object value, boolean done) {
			super();
			this.value = value;
			this.done = done;
		}
		
	}
	
	
	public static class DbOperation implements Serializable{
		 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public String type;
		public String key;
		public String value;
		public String from;
		public UUID requestId;
		public DbOperation(String type, String key, String value, String from,
				UUID requestId) {
			super();
			this.type = type;
			this.key = key;
			this.value = value;
			this.from = from;
			this.requestId = requestId;
		}
		@Override
		public String toString() {
			return "Operation [" + type +" "+ key +
					" " + value + ", fromClient=" + from + ", requestId=" + requestId
					+ "]";
		}
		
	}
	public static class ShardOperation implements Serializable{
		 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public String type;
		public Integer groupId;
		public List<Integer> servers;
		public Integer shard;
		public UUID requestId;
		
		public ShardOperation(String type, Integer groupId,
				List<Integer> servers, Integer shard, UUID requestId) {
			super();
			this.type = type;
			this.groupId = groupId;
			this.servers = servers;
			this.shard = shard;
			this.requestId = requestId;
		}
		@Override
		public String toString() {
			return "ShardOperation [type=" + type + ", groupId=" + groupId
					+ ", servers=" + servers + ", shard=" + shard
					+ ", requestId=" + requestId + "]";
		}


	}
	
}
