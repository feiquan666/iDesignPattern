package cn.javaunion.core.patterns.chain.special;

import cn.javaunion.core.patterns.chain.BaseChainContext;
import cn.javaunion.core.patterns.chain.BaseNode;
import cn.javaunion.utils.Assert;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

/**
 * <p>Description: 一种支持并发运行节点的特殊节点</p>
 * <p>@Date: 2022-12-04 14:03:04</p>
 * <p>@Author: 飞拳</p>
 */
public class ParallelNode extends BaseSpecialNode {

	private final Logger log = Logger.getLogger(this.getClass().getName());
	private final List<BaseNode> parallelNodes;
	/**
	 * <p>线程池，并发节点必须提供一个线程池</p>
	 */
	private final Executor executor;

	public ParallelNode(List<BaseNode> parallelNodes, Executor executor) {
		for (BaseNode node : parallelNodes) {
			Assert.notNull(node, "并行任务节点中存在将引发空指针异常的节点，终止责任链构建");
			Assert.isTrue(!(node instanceof BaseSpecialNode), "特殊节点中不允许嵌套特殊节点");
		}
		this.parallelNodes = parallelNodes;
		Assert.notNull(executor, "线程池不允许为空");
		this.executor = executor;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void handle(BaseChainContext context) {
		log.info("############ " + this.getClass().getSimpleName() + " Start ############ ");
		if (parallelNodes != null && parallelNodes.size() > 0) {
			CompletableFuture[] futures = new CompletableFuture[parallelNodes.size()];
			int i = -1;
			for (BaseNode parallelNode : parallelNodes) {
				Assert.notNull(parallelNode, "任务节点将引发空指针异常，终止执行");
				CompletableFuture<Void> future = CompletableFuture.runAsync(
						() -> parallelNode.handle(context));
				futures[i += 1] = future;
			}
			CompletableFuture.allOf(futures).join();
			log.info("############ " + this.getClass().getSimpleName() + " End ############ ");
		}
	}
}
