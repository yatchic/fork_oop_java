package family_tree.date;

import family_tree.addon.Addon;
import family_tree.family.FamilyTree;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class DateClass extends DataAbstractClass{


    /**
     * Возвращает количество дней от первого года по заданную дату.
     */

    public int calculateDays(String dateString) {
        Addon a = new Addon();
        FamilyTree f = new FamilyTree();

        int days = Integer.parseInt(a.findText(dateString, "^\\d\\d"));
        int month = Integer.parseInt(a.findText(dateString, "(?<=^\\d\\d\\.)\\d\\d(?=\\.\\d{4})"));
        int year = Integer.parseInt(a.findText(dateString, "(?<=^\\d\\d\\.\\d\\d\\.)\\d{4}"));
          return   (days+(days*month)+(8640*year));
    }


    /**
     * Возвращает возраст в годах , из даты рождения  вычетает дату смерти.
     * @param birthDateStr дата рождения в формате "дд.мм.гггг"
     * @param deathDateStr дата смерти в формате "дд.мм.гггг"
     * @return
     */
    @Override
    public int raznitsaVremeni(String birthDateStr, String deathDateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date birthDate = dateFormat.parse(birthDateStr);
            Date deathDate = dateFormat.parse(deathDateStr);
            LocalDate localBirthDate = birthDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            LocalDate localDeathDate = deathDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            Period period = Period.between(localBirthDate, localDeathDate);
            return period.getYears();
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * Возвращает возраст в годах из сегодняшней даты вычетает дату рождения.
     */
    @Override
    public int calculateAge(String birthDateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date birthDate = dateFormat.parse(birthDateStr);
            LocalDate localBirthDate = birthDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            LocalDate today = LocalDate.now();
            Period period = Period.between(localBirthDate, today);
            return period.getYears();
        } catch (ParseException e) {
            return 0;
        }
    }
}
