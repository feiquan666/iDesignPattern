package cn.javaunion.core.patterns.chain.special;

import cn.javaunion.core.patterns.chain.BaseChainContext;
import cn.javaunion.core.patterns.chain.BaseNode;
import cn.javaunion.utils.Assert;
import java.util.Objects;

/**
 * <p>Description: 一种满足条件后才运行的节点</p>
 * <p>@Date: 2022-12-04 14:03:04</p>
 * <p>@Author: 飞拳</p>
 */
public class ConditionNode extends BaseSpecialNode {

	/**
	 * <p>运行条件</p>
	 */
	private final Boolean condition;

	/**
	 * <p>满足条件后执行的节点</p>
	 */
	private final BaseNode yesNode;
	/**
	 * <p>条件不满足且有 noNode 节点才执行</p>
	 */
	private BaseNode noNode;

	public ConditionNode(Boolean condition, BaseNode yesNode) {
		this(condition, yesNode, null);
	}

	public ConditionNode(Boolean condition, BaseNode yesNode, BaseNode noNode) {
		Assert.notNull(condition, "执行条件为空，终止责任链构建");
		Assert.notNull(yesNode, "执行节点将引发空指针异常，终止责任链构建");
		Assert.isTrue(!(yesNode instanceof BaseSpecialNode), "特殊节点中不允许嵌套特殊节点");
		this.condition = condition;
		this.yesNode = yesNode;
		if (Objects.nonNull(noNode)) {
			Assert.isTrue(!(noNode instanceof BaseSpecialNode), "特殊节点中不允许嵌套特殊节点");
			this.noNode = noNode;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void handle(BaseChainContext context) {
		if (condition) {
			yesNode.handle(context);
		} else if (Objects.nonNull(noNode)) {
			noNode.handle(context);
		}
	}
}
