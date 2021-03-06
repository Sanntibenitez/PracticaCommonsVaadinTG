package com.topgroup.capa.base.business.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.topgroup.capa.base.domain.model.Producto;
import com.topgroup.capa.base.domain.model.Provincia;
import com.topgroup.capa.base.domain.model.TipoProducto;
import com.topgroup.capa.base.persistence.filter.ProductoFilter;

public class PracticeServiceMockImpl implements PracticeService {

	private List<Producto> productos = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.topgroup.capa.base.business.service.PracticeService#filter(com.topgroup
	 * .commons.utils.lang.BaseFilter)
	 */
	@Override
	public List<Producto> filter(ProductoFilter filter) {
		ProductoFilter pFilter = (ProductoFilter) filter;
		List<Producto> allProductos = findAll();

		List<Producto> productos = new ArrayList<>();
		for (Producto p : allProductos) {
			if ((pFilter.getCodigo() == null || p.getCodigo().startsWith(
					pFilter.getCodigo()))
					&& (pFilter.getTipoProducto() == null || p
							.getTipoProducto()
							.equals(pFilter.getTipoProducto()))) {
				productos.add(p);
			}
		}

		return productos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.topgroup.capa.base.business.service.PracticeService#findAll()
	 */
	@Override
	public List<Producto> findAll() {
		if (productos == null) {
			productos = new ArrayList<>();

			List<TipoProducto> tipos = findAllTipoProductos();

			for (long i = 1; i < 101; i++) {
				Producto prod = new Producto();
				prod.setId(i);
				prod.setCodigo("Prod" + i);
				prod.setDescripcion("Producto destinado " + i);
				prod.setTipoProducto(tipos.get((int) (i % 10)));
				productos.add(prod);

			}
		}
		return productos;
	}

	private List<TipoProducto> findAllTipoProductos() {
		List<TipoProducto> tipos = new ArrayList<>();
		List<Provincia> provincias = findAllProvincias();
		for (long i = 1; i < 11; i++) {
			TipoProducto tp1 = new TipoProducto();
			tp1.setId(i);
			tp1.setDescripcion("Tipo de producto " + i);
			tp1.setProvincia(provincias.get((int) (i % 5)));
			tipos.add(tp1);

		}
		return tipos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.topgroup.capa.base.business.service.PracticeService#count(com.topgroup
	 * .commons.utils.lang.BaseFilter)
	 */
	@Override
	public long count(ProductoFilter filter) {
		return filter(filter).size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.topgroup.capa.base.business.service.PracticeService#save(com.topgroup
	 * .capa.base.view.bean.ProductoViewBean)
	 */
	@Override
	public void save(Producto bean) {
		List<Producto> prod = findAll();
		if (!prod.contains(bean)) {
			prod.add(bean);
		} else {
			prod.remove(bean);
			prod.add(bean);
		}
	}

	@Override
	public List<TipoProducto> findTipoProductosByProvincia(final long idProvincia) {
			List<TipoProducto> tipoProductos = findAllTipoProductos();
				CollectionUtils.filter(tipoProductos, new Predicate() {
					@Override
					public boolean evaluate(Object object) {
						return ((TipoProducto)object).getProvincia().getId().equals(idProvincia);
					}
				});
		return tipoProductos;
	}

	@Override
	public List<Provincia> findAllProvincias() {
		List<Provincia> provincias = new ArrayList<>();
		for (long i = 1; i < 6; i++) {
			Provincia tp1 = new Provincia();
			tp1.setId(i);
			tp1.setNombre("Provincia " + i);
			provincias.add(tp1);

		}
		return provincias;
	}

}
