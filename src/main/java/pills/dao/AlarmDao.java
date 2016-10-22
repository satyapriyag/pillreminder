/**
 * 
 */
package pills.dao;

import java.util.List;

import pills.entity.Alarm;

/**
 * @author satya
 *
 */
public interface AlarmDao{
	public void save(Alarm alarm);
	public void delete(Alarm alarm);
	public List<Alarm> getAll();
	public void update(Alarm alarm);
	public Alarm getById(Integer id);
	public List<Alarm> getByUserId(Integer UserId);
	public List<Alarm> getByRecurrence(int time);
}
