/**
 * 
 */
package pills.dao;

import java.util.List;

import pills.entity.Alternative;

/**
 * @author satya
 *
 */
public interface AlternativeDao{
//	public void save(Alternative alternative);
//	public void delete(Alternative alternative);
//	public List<Alternative> getAll();
//	public void update(Alternative alternative);
	public Alternative getById(Integer id);
	public List<Alternative> getByPillId(Integer PillId);
}
