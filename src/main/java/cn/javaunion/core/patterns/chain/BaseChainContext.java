package cn.javaunion.core.patterns.chain;

import java.util.Objects;

/**
 * <p>Description: 责任链上下文</p>
 * <p>@Date: 2022-12-04 14:03:04</p>
 * <p>@Author: 飞拳</p>
 */
public class BaseChainContext<Req, Resp> {

	/**
	 * <p>原始请求参数</p>
	 */
	private final Req req;

	/**
	 * <p>最终返回参数</p>
	 */
	private Resp resp;

	/**
	 * <p>请求者用户 ID</p>
	 */
	private Long userId;

	public BaseChainContext(Req req) {
		this.req = req;
	}

	public Req getReq() {
		return req;
	}

	public Resp getResp() {
		return resp;
	}

	public void setResp(Resp resp) {
		this.resp = resp;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BaseChainContext<?, ?> that = (BaseChainContext<?, ?>) o;
		return Objects.equals(req, that.req) && Objects.equals(resp, that.resp)
				&& Objects.equals(userId, that.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(req, resp, userId);
	}
}
