package org.motechproject.nms.mobileacademy.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.motechproject.mtraining.domain.Bookmark;
import org.motechproject.mtraining.repository.BookmarkDataService;
import org.motechproject.nms.mobileacademy.domain.Course;

import org.motechproject.nms.mobileacademy.dto.MaBookmark;
import org.motechproject.nms.mobileacademy.repository.CourseDataService;
import org.motechproject.nms.mobileacademy.service.MobileAcademyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple implementation of the {@link MobileAcademyService} interface.
 */
@Service("mobileAcademyService")
public class MobileAcademyServiceImpl implements MobileAcademyService {

    /**
     * Bookmark data service
     */
    private BookmarkDataService bookmarkDataService;

    /**
     * Course data service
     */
    private CourseDataService courseDataService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MobileAcademyServiceImpl.class);

    @Autowired
    public MobileAcademyServiceImpl(BookmarkDataService bookmarkDataService,
                                    CourseDataService courseDataService) {
        this.bookmarkDataService = bookmarkDataService;
        this.courseDataService = courseDataService;
    }

    @Override
    public Course getCourse() {

        // Make this course name configurable
        String lookupName = "MobileAcademyCourse";
        return courseDataService.findCourseByName(lookupName);
    }

    @Override
    public void setCourse(Course course) {
        Course existing = courseDataService.findCourseByName(course.getName());

        if (existing == null) {
            courseDataService.create(course);
        } else {
            course.setId(existing.getId());
            courseDataService.update(course);
        }
    }

    @Override
    public int getCourseVersion() {

        Course course = getCourse();
        if (course != null) {
            DateTime version = course.getModificationDate();
            return (int) version.getMillis();
        } else {
            // return -1 and let the caller handle the upstream response
            return -1;
        }
    }

    @Override
    public MaBookmark getBookmark(Long callingNumber, Long callId) {

        List<Bookmark> bookmarks = bookmarkDataService.findBookmarksForUser(callingNumber.toString());
        if (CollectionUtils.isEmpty(bookmarks)) {
            return null;
        } else {

            if (bookmarks.size() > 1) {
                LOGGER.debug("Found more than 1 instance of valid bookmark, picking top");
            }

            Bookmark existingBookmark = bookmarks.get(0);
            MaBookmark toReturn = new MaBookmark();
            toReturn.setCallingNumber(Long.parseLong(existingBookmark.getExternalId()));
            toReturn.setCallId(callId);

            if (existingBookmark.getProgress() != null) {
                toReturn.setScoresByChapter((Map<String, Integer>) existingBookmark.getProgress()
                        .get("scoresByChapter"));
                toReturn.setBookmark(existingBookmark.getChapterIdentifier() + "_" +
                        existingBookmark.getLessonIdentifier());
            } else {
                toReturn.setScoresByChapter(null);
                toReturn.setBookmark(null);
            }


            return toReturn;
        }
    }

    @Override
    public void setBookmark(MaBookmark saveBookmark) {

        String callingNumber = saveBookmark.getCallingNumber().toString();
        List<Bookmark> existing = bookmarkDataService.findBookmarksForUser(callingNumber);

        if (CollectionUtils.isEmpty(existing)) {
            // if no bookmarks exist for user
            LOGGER.info("No bookmarks found for user " + callingNumber);
            bookmarkDataService.create(setBookmarkProperties(saveBookmark, new Bookmark()));
        } else {
            // error check
            if (existing.size() > 1) {
                LOGGER.error("Found more than 1 bookmark for calling number. This should never be possible.");
                LOGGER.error("Contact dev team about calling number: " + callingNumber);
            }

            // update the first bookmark
            LOGGER.info("Updating the first bookmark for user");
            bookmarkDataService.update(setBookmarkProperties(saveBookmark, existing.get(0)));
        }
    }

    private Bookmark setBookmarkProperties(MaBookmark fromBookmark, Bookmark toBookmark) {

        toBookmark.setExternalId(fromBookmark.getCallingNumber().toString());

        if (toBookmark.getProgress() == null) {
            toBookmark.setProgress(new HashMap<String, Object>());
        }
        toBookmark.getProgress().put("callId", fromBookmark.getCallId());

        // This guarantees that we always update to the latest scores
        if (fromBookmark.getScoresByChapter() != null) {
            toBookmark.getProgress().put("scoresByChapter", fromBookmark.getScoresByChapter());
        }

        if (fromBookmark.getBookmark() != null) {
            toBookmark.setChapterIdentifier(fromBookmark.getBookmark().split("_")[0]);
            toBookmark.setLessonIdentifier(fromBookmark.getBookmark().split("_")[1]);
        }

        return toBookmark;
    }

}
