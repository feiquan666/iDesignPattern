package cn.javaunion.core.patterns.chain;

import cn.javaunion.utils.Assert;
import java.util.LinkedList;

/**
 * <p>Description: 责任链管理器</p>
 * <p>@Date: 2022-12-04 14:03:04</p>
 * <p>@Author: 飞拳</p>
 */
public abstract class BaseChainManager<Req, Resp> {

	private final LinkedList<BaseNode> nodeList;

	private Resp resp;

	public BaseChainManager(LinkedList<BaseNode> nodeList) {
		this.nodeList = nodeList;
	}

	/**
	 * <p>添加节点</p>
	 */
	public BaseChainManager<Req, Resp> addNode(BaseNode node) {
		Assert.notNull(node, "任务节点将引发空指针异常，终止责任链构建");
		nodeList.add(node);
		return this;
	}

	public BaseChainManager<Req, Resp> addNodes(BaseNode ... nodes) {
		for (BaseNode node : nodes) {
			addNode(node);
		}
		return this;
	}

	/**
	 * <p>启动</p>
	 */
	public BaseChainManager<Req, Resp> start(Req req) {
		return start(req, null);
	}

	@SuppressWarnings("unchecked")
	public BaseChainManager<Req, Resp> start(Req req, Long userId) {
		final BaseChainContext<Req, Resp> context = initChainContext(req);
		context.setUserId(userId);
		nodeList.forEach(node -> node.handle(context));
		resp = context.getResp();
		return this;
	}

	protected abstract BaseChainContext<Req, Resp> initChainContext(Req req);

	/**
	 * <p>获取结果</p>
	 */
	public Resp getResp() {
		return resp;
	}
}
