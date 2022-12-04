package cn.javaunion.core.patterns.chain;

/**
 * <p>Description: 抽象责任链节点</p>
 * <p>@Date: 2022-12-04 14:02:10</p>
 * <p>@Author: 飞拳</p>
 */
public abstract class BaseNode<Ctx extends BaseChainContext> {

	/**
	 * <p>description: </p>
	 * @param context 上下文
	 * @Date: 2022-12-04 14:02:10
	 * @Author: 飞拳
	 */
	public abstract void handle(Ctx context);

}
