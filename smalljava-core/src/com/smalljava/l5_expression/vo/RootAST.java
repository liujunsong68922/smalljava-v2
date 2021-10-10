package com.smalljava.l5_expression.vo;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smalljava.l5_expression.vo.constvalue.AbstractConstDataElement;
import com.smalljava.l5_expression.vo.one.AbstractSingleOperDataOperElement;
import com.smalljava.l5_expression.vo.two.DualOperDataOperElement;
import com.smalljava.l5_expression.vo.var.VarDataElement;
import com.smalljava.l5_expression.vo.var.VarDefineOperElement;

/**
 * RootAST代表一个需要进一步进行分解处理的中间状态，在这个节点下继续加子节点
 * 
 * @author liujunsong
 *
 */
public class RootAST extends AbstractAST {
	private Logger logger = LoggerFactory.getLogger(RootAST.class);
	
	private String strexpression;
	/**
	 * MEMO系统生成的代表节点唯一标识的uuid
	 */
	private String uuid;

	// 构造函数
	public RootAST() {
		// 生成唯一的uuid
		this.uuid = UUID.randomUUID().toString().replaceAll("-", "");
	}

	public String getStrexpression() {
		return strexpression;
	}

	public void setStrexpression(String strexpression) {
		this.strexpression = strexpression;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void show(int ilevel) {
		if (this.getChildren() == null) {
			return;
		}
		// logger.info(this.getClass().getSimpleName());
		String blockname = this.getClass().getSimpleName();
		String strleft = "";
		for (int i = 0; i < ilevel; i++) {
			strleft += "    ";
		}

		String sinfo = this.strexpression;
		if (this instanceof DualOperDataOperElement) {
			DualOperDataOperElement e1 = (DualOperDataOperElement) this;
			sinfo = e1.getOpercode();
		}
		if (this instanceof VarDataElement) {
			VarDataElement var = (VarDataElement) this;
			sinfo = var.getVarname();
		}
		if (this.getChildren().size() == 0) {
			if (this instanceof AbstractConstDataElement) {
				AbstractConstDataElement constdata = (AbstractConstDataElement) this;
				logger.info(strleft + "---->" + blockname + ":" + constdata.getDatavalue());
			} else if (this instanceof VarDataElement) {
				VarDataElement var = (VarDataElement) this;
				logger.info(strleft + "---->" + blockname + ":" + var.getVarname());
			} else if (this instanceof VarDefineOperElement) {
				VarDefineOperElement def = (VarDefineOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + def.getDatatype() + " " + def.getVarname());
			} else if (this instanceof AbstractSingleOperDataOperElement) {
				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + se.getOpercode());
			}

			else {
				logger.info(strleft + "no child---->" + blockname + ":" + sinfo);
			}
			return;
		} else {
			if (this instanceof AbstractSingleOperDataOperElement) {
				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + se.getOpercode());
			} else {
				logger.info(strleft + "---->" + blockname + ":" + sinfo);
			}
		}

		for (RootAST child : this.getChildren()) {
			if (child != null) {
				child.show(ilevel + 1);
			} else {
				logger.info("child is null" + sinfo);
			}
		}

	}
	
	public String getShowString(int ilevel) {
		String sret = "";
		if (this.getChildren() == null) {
			return sret;
		}

		String blockname = this.getClass().getSimpleName();
		String strleft = "";
		for (int i = 0; i < ilevel; i++) {
			strleft += "    ";
		}

		String sinfo = this.strexpression;
		if (this instanceof DualOperDataOperElement) {
			DualOperDataOperElement e1 = (DualOperDataOperElement) this;
			sinfo = e1.getOpercode();
		}
		if (this instanceof VarDataElement) {
			VarDataElement var = (VarDataElement) this;
			sinfo = var.getVarname();
		}
		if (this.getChildren().size() == 0) {
			if (this instanceof AbstractConstDataElement) {
				AbstractConstDataElement constdata = (AbstractConstDataElement) this;
				logger.info(strleft + "---->" + blockname + ":" + constdata.getDatavalue());
				sret +="\r\n";
				sret += (strleft + "---->" + blockname + ":" + constdata.getDatavalue());
			} else if (this instanceof VarDataElement) {
				VarDataElement var = (VarDataElement) this;
				logger.info(strleft + "---->" + blockname + ":" + var.getVarname());
				sret +="\r\n";
				sret +=(strleft + "---->" + blockname + ":" + var.getVarname());
			} else if (this instanceof VarDefineOperElement) {
				VarDefineOperElement def = (VarDefineOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + def.getDatatype() + " " + def.getVarname());
				sret += "\r\n";
				sret += (strleft + "---->" + blockname + ":" + def.getDatatype() + " " + def.getVarname());
			} else if (this instanceof AbstractSingleOperDataOperElement) {
				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + se.getOpercode());
				sret += "\r\n";
				sret += (strleft + "---->" + blockname + ":" + se.getOpercode());
			}

			else {
				logger.info(strleft + "no child---->" + blockname + ":" + sinfo);
				sret += "\r\n";
				sret += (strleft + "no child---->" + blockname + ":" + sinfo);
			}
			return sret;
		} else {
			if (this instanceof AbstractSingleOperDataOperElement) {
				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + se.getOpercode());
				sret +="\r\n";
				sret +=(strleft + "---->" + blockname + ":" + se.getOpercode());
			} else {
				logger.info(strleft + "---->" + blockname + ":" + sinfo);
				sret +="\r\n";
				sret +=(strleft + "---->" + blockname + ":" + sinfo);
			}
		}

		for (RootAST child : this.getChildren()) {
			if (child != null) {
				String schild = child.getShowString(ilevel + 1);
				sret += schild;
			} else {
				logger.info("child is null" + sinfo);
				sret +="\r\n"+("child is null" + sinfo);
			}
		}
		return sret;

	}	
}
