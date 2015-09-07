package hudson.plugins.sloccount;

import hudson.Extension;
import hudson.model.AbstractBuild;
import org.jenkinsci.plugins.codehealth.LinesOfCode;
import org.jenkinsci.plugins.codehealth.LinesOfCodeProvider;

/**
 * @author Michael Prankl
 */
@Extension
public class SloccountLoCProvider extends LinesOfCodeProvider {

    @Override
    public LinesOfCode getLOC(AbstractBuild<?, ?> build) {
        final SloccountBuildAction action = build.getAction(SloccountBuildAction.class);
        if (action != null) {
            int fileCount = action.getResult().getStatistics().getFileCount();
            int lineCount = action.getResult().getStatistics().getLineCount();
            return new LinesOfCode(lineCount, fileCount);
        }
        return null;
    }

    @Override
    public String getOrigin() {
        return "sloccount";
    }
}
